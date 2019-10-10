package com.wzb.businessservice.impl;

import com.netflix.discovery.converters.Auto;
import com.wzb.businessservice.CalculationService;
import com.wzb.businessservice.feignservice.*;
import com.wzb.businessservice.utils.RegexUtil;
import com.wzb.common.NWResult;
import com.wzb.common.RI;
import com.wzb.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 19:01
 * @description:
 */
@Service
public class CalculationServiceImpl implements CalculationService {

    @Autowired
    private TreeNodeDBService treeNodeDBService;

    @Autowired
    private AdjacentClosureDBService adjacentClosureDBService;

    @Autowired
    private ProjectInformationDBService projectInformationDBService;

    @Autowired
    private ConclusionDBService conclusionDBService;

    @Autowired
    private NormalizationWeightDBService normalizationWeightDBService;

    @Autowired
    private DocInfo2DBservice docInfo2DBservice;

    @Autowired
    private MatrixStorageDBService matrixStorageDBService;

    @Override
    public NWResult NWCalculation(Double[][] data) {
        NWResult nwResult = new NWResult();
        int n = data[0].length;
        //归一化后的矩阵data1
        Double[][] data1 = new Double[n][n];
        Double[] W;
        Double CR;
        //每一列都归一化
        //data[i][j] = data[i][j]/∑1到n a[i][j]
        Double sum0;
        //sum0计算出错
        for(int i = 0;i<n;i++){
            //一共n列要归一n次
            //求和置零
            sum0 = 0.0;
            //计算总和
            for(int k=0;k<n;k++){
                sum0 = sum0 + data[k][i];

            }

            System.out.println("sum0:"+String.valueOf(sum0));
            for(int j = 0;j<n;j++){
                //每一列都需要归一n次

                //归一
                data1[j][i] = data[j][i]/sum0;
            }
        }
        //将每一列经归一化处理后的判断矩阵按行相加
        W = new Double[n];
        for(int i = 0; i<n;i++){
            //一共n行
            //求和置零
            sum0 = 0.0;
            for(int j=0;j<n;j++){
                sum0 = sum0 + data1[i][j];
            }
            //一行求和完毕
            W[i] = sum0;

        }
        //对向量W进行归一化处理
        //求和置零
        sum0 = 0.0;
        for(int i = 0;i<n;i++){
            sum0 = sum0+W[i];
        }
        for(int i = 0;i<n;i++){
            //归一
            W[i] = W[i]/sum0;
            System.out.println("W[i]"+String.valueOf(W[i]));
        }
        //计算判断矩阵最大特征根lambda λmax
        //λmax = 1/n * ∑i=1到n  ∑j=1到n a[i][j]*W[j] /W[i]
        Double lambdaMax = 0.0;
        //求和置零
        sum0 = 0.0;
        Double sum1;
        for(int i = 0;i<n;i++){
            //求和置零
            sum1 = 0.0;
            for(int j = 0;j<n;j++){
                sum1 = sum1 + data[i][j] * W[j];
            }
            sum0 = sum0+sum1/W[i];
            System.out.println("sum1:"+String.valueOf(sum1));
            System.out.println("sum01:"+String.valueOf(sum0));
        }

//            lambdaMax = 1/n * sum0;
        lambdaMax = sum0/n;
        System.out.println("lambdaMax:"+String.valueOf(lambdaMax));
        //决定各矩阵的一致性指标
        //判断矩阵一致性指标C.I.(Consistency   Index)
        Double CI = (lambdaMax - n)/(n-1);
        System.out.println("CI:"+String.valueOf(CI));
        //获取RI
        Double myRI = Double.valueOf(RI.RILIST.get(n-1));
        System.out.println("myRI"+String.valueOf(myRI));
        //计算C.R.
        CR = CI/myRI;
        System.out.println("CR:"+String.valueOf(CR));

        nwResult.setData(data1).setCR(CR).setW(W);


        return nwResult;
    }

    @Override
    public void conclusionCalculation() {
        int depth=0;
        //获取项目名代表的节点ID（根节点ID
        TreeNodeContent rootTreeNode = treeNodeDBService.selByContent(projectInformationDBService.selNowModel().getProjectName());
        System.out.println("rootTreeNode:"+rootTreeNode.toString());


        Integer rootID = rootTreeNode.getId();

        //获取项目深度
        //以根节点为祖先的所有节点
        List<AdjacentClosure> rootAnscstor = adjacentClosureDBService.selByAncestor(rootID);
//        QueryBuilder<AdjacentClosure> aqb = adjacentClosureDao.queryBuilder();
//        rootAnscstor = aqb.where(aqb.and(AdjacentClosureDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                ,AdjacentClosureDao.Properties.Ancestor.eq(rootID)))
//                .list();

        //建立一个列表代表最深的一层的所有节点
        List<AdjacentClosure> deepest = new ArrayList<>();
        //获取最深的深度
        for(AdjacentClosure adjacentClosure : rootAnscstor){
            if(adjacentClosure.getDepth()>depth){
                depth = adjacentClosure.getDepth();
            }
        }

        for(AdjacentClosure adjacentClosure : rootAnscstor){
            if(adjacentClosure.getDepth().equals(depth)){
                deepest.add(adjacentClosure);
            }
        }

        //最后一层权重
        List<Double> lastWeight = new ArrayList<>();

        //todo:将最后一层与结论层的归一矩阵 转换成一个m*n的矩阵
        // m->中间层（准则层最后一层）的准则个数 n->结论个数
        //获取所有结论
        List<Conclusion> conList = new ArrayList<>();
        List<Double> wcList = new ArrayList<>();

        conList = conclusionDBService.selByModel();
//        QueryBuilder<Conclusion> cqb = conclusionDao.queryBuilder();
//        conList = cqb.where(ConclusionDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)).list();

        if(conList == null){
            System.out.println("请先添加结论！");
//            Log.e("请先添加结论！","1");
//            return null;
            return;
        }

        Integer nodeID;
//        Long nodeID;
        String nowValue;

        for(int i = 0;i<deepest.size();i++){
            //获取当前节点ID
            AdjacentClosure nowA = deepest.get(i);
//            nodeID = Long.valueOf(nowA.getDescendant());
            nodeID = nowA.getDescendant();
            //根据ID获取值
            TreeNodeContent myTreeNode = treeNodeDBService.selById(nodeID);
//            QueryBuilder<TreeNodeContent> tqb1 = treeNodeContentDao.queryBuilder();
//            TreeNodeContent myTreeNode = tqb1.where(tqb1.and(TreeNodeContentDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                    ,TreeNodeContentDao.Properties.Id.eq(nodeID)))
//                    .unique();

            System.out.println("myTreeNode:"+myTreeNode.toString());
            nowValue = myTreeNode.getValue();
            //从归一权重中取值

            NormalizationWeight normalizationWeight = normalizationWeightDBService.selByNodeValue(nowValue);
//            QueryBuilder<NormalizationWeight> nqb = normalizationWeightDao.queryBuilder();
//            normalizationWeight = nqb.where(nqb.and(NormalizationWeightDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                    ,NormalizationWeightDao.Properties.NextVlue.eq(nowValue)))
//                    .unique();
            //给当前节点赋值初始值为以当前节点为nextValue的归一权重表中的数据
            lastWeight.add(normalizationWeight.getWeight());

            //从数据库归一权重表中查找以最后一层节点为当前值 以conclusion为nextValue的归一权重加入wc
            //取最后一层与结论层的值
            System.out.println("conList.size()"+String.valueOf(conList.size()));
            for(int j = 0;j<conList.size();j++){
//                nqb=normalizationWeightDao.queryBuilder();
//                Log.e("nqb1",nqb.list().toString());
                System.out.println("nowValue:>"+nowValue);
//                Log.e("nowValue",nowValue);
                System.out.println("conList.get(j):"+conList.get(j).toString());
//                Log.e("conList.get(j)",conList.get(j).toString());
                NormalizationWeight myNor = new NormalizationWeight();
                myNor = normalizationWeightDBService.selByTwoValue(nowValue,conList.get(j).getPlan());
//                myNor = nqb.where(nqb.and(NormalizationWeightDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                        ,NormalizationWeightDao.Properties.Value.eq(nowValue)
//                        ,NormalizationWeightDao.Properties.NextVlue.eq(conList.get(j).getPlan())))
//                        .unique();
                System.out.println("myNor"+myNor.toString());
//                Log.e("myNor",myNor.toString());
                wcList.add(myNor.getWeight());
            }
//                Log.e("wcList",wcList.toString());

            //从数据库中查询当前节点有没有父节点
            //父节点就是在邻接矩阵中后裔ID为当前节点ID且深度为1的节点
            AdjacentClosure fatherAdj = adjacentClosureDBService.selByDescendant(nodeID);
//            QueryBuilder<AdjacentClosure> aqb1 = adjacentClosureDao.queryBuilder();
//            AdjacentClosure fatherAdj = aqb1.where(aqb1.and(AdjacentClosureDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                    ,AdjacentClosureDao.Properties.Descendant.eq(nodeID)
//                    ,AdjacentClosureDao.Properties.Depth.eq(1)))
//                    .unique();

            //当父节点不为根节点且存在
            //root节点一般代表项目名的节点
            while((fatherAdj.getAncestor()!=rootID.intValue())&&(fatherAdj!=null)){

                //获取父节点权重
                //父节点ID
//                Long fNodeID = Long.valueOf(fatherAdj.getAncestor());
                Integer fNodeID = fatherAdj.getAncestor();
                TreeNodeContent ftnc = treeNodeDBService.selById(fNodeID);
//                TreeNodeContent ftnc = tqb.where(tqb.and(TreeNodeContentDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                        ,TreeNodeContentDao.Properties.Id.eq(fNodeID)))
//                        .unique();
                String fValue = ftnc.getValue();
                NormalizationWeight fnw = normalizationWeightDBService.selByNodeValue(fValue);
//                NormalizationWeight fnw = nqb.where(nqb.and(NormalizationWeightDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                        ,NormalizationWeightDao.Properties.NextVlue.eq(fValue)))
//                        .unique();
                //当前节点权重乘以父节点权重
                lastWeight.set(i,lastWeight.get(i)*fnw.getWeight());
                //再往上找父节点直到找到根节点为止
                fatherAdj = adjacentClosureDBService.selByDescendant(fNodeID);
//                fatherAdj = aqb1.where(aqb1.and(AdjacentClosureDao.Properties.ProjectName.eq(Constant.PROJECT_NAME)
//                        ,AdjacentClosureDao.Properties.Descendant.eq(fNodeID)
//                        ,AdjacentClosureDao.Properties.Depth.eq(1)))
//                        .unique();
            }
        }

        System.out.println("lastWeight:"+lastWeight.toString());
        //到此得到了一个n*1的矩阵（waph）


        //获取wc
        //行应该是结论
        //列应该是准则层最后一层
        int n = conList.size();//行
        int m = lastWeight.size();//列

        System.out.println("wcList:"+String.valueOf(wcList.size()));
//        Log.e("wcList",String.valueOf(wcList.size()));

        Double[][]  wc = new Double[n][m];
        int count=0;

//            for(int i = 0;i<n;i++){
//                for(int j = 0;j<m;j++){
//                    //从list中依次取值并且count自增
//                    wc[i][j] = wcList.get(count++);
//                    Log.e("wc[i][j]",String.valueOf(wc[i][j]));
//                }
//            }
        //上述的是错误的 因为wcList.get(count++);是从数据库中取出来的排列是一列一列的
        //n=3,m=6
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                //从list中依次取值并且count自增
                System.out.println("j and i:"+String.valueOf(j + " " + i));
//                Log.e("j and i",String.valueOf(j + " " + i));
                wc[j][i] = wcList.get(count++);
                System.out.println("wc[i][j]:"+String.valueOf(wc[j][i]));
//                Log.e("wc[i][j]",String.valueOf(wc[j][i]));
            }
        }

        System.out.println("depth:"+String.valueOf(depth));
//        Log.e("depth:",String.valueOf(depth));

        //矩阵计算wc * waph
        List<Double> resList = new ArrayList<>();
        Double res;
        for(int i = 0;i<n;i++){
            res=0.0;
            for(int k = 0;k<m;k++){
                System.out.println("wc[i][k]*last:"+String.valueOf(wc[i][k]*lastWeight.get(k)));
                res += wc[i][k]*lastWeight.get(k);
            }
            resList.add(res);
            System.out.println("res:"+String.valueOf(res));
//            Log.e("res",String.valueOf(res));
        }

        //存入数据库
        Conclusion myCon = new Conclusion();
        for(int i = 0;i<resList.size();i++){
            myCon = conList.get(i);
            myCon.setPriority(Float.valueOf(resList.get(i).toString()));
            conclusionDBService.updByConclusion(myCon);
//            conclusionDao.update(myCon);
        }

    }

    @Override
    public void criConCalculation() {
        //从doc_info2表中获取前十位医生的数据
        List<DocInfo2> docList = docInfo2DBservice.findCountDocInfo(10);
        System.out.println(docList.toString());
        //转为初始矩阵（五个准则-10个结论 5个10*10的矩阵）
        Double[][][] allData= new Double[5][10][10];
        //将十位医生的信息转换为矩阵
        //取出五个准则的数据
        Double[][] criterionData = new Double[5][10];
        DocInfo2 docInfo2 = null;
        //五行分别代表物种准则:预约量，问诊量，综合评分，视话价格，图文价格
        List<String> criterionList = new ArrayList<>();
        criterionList.add("预约量");
        criterionList.add("问诊量");
        criterionList.add("综合评分");
        criterionList.add("视话价格");
        criterionList.add("图文价格");
        for (int i = 0; i < 10; i++) {
            docInfo2 = docList.get(i);
            criterionData[0][i] = Double.parseDouble(docInfo2.getNapm());
            criterionData[1][i] = Double.parseDouble(docInfo2.getNacc());
            criterionData[2][i] = Double.parseDouble(docInfo2.getErate());
            //视话价格，图文价格带了钱的中文字符需要使用正则表达式截取数字
            criterionData[3][i] = Double.parseDouble(RegexUtil.getNumbers(docInfo2.getShihuaprice()));
            criterionData[4][i] = Double.parseDouble(RegexUtil.getNumbers(docInfo2.getTuweiprice()));
        }

        Double[] currentCriterion = new Double[10];
        MatrixStorage matrixStorage = new MatrixStorage();
        String projectName = projectInformationDBService.selNowModel().getProjectName();
        for (int i = 0; i < 5; i++) {
            // 使用currentCriterion将准则数据分为五份分别对比插入正确位置
            currentCriterion = criterionData[i];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    // 只需输入上三角数据
                    // 对称轴数据为1下三角数据为上三角倒数
                    // 层次分析法不存在负数，只有(1,+..)以及(0,1)两种数罢了
                    if (j<k){
                        //上三角
                        allData[i][j][k] = currentCriterion[j]/currentCriterion[k];
                    }else if (j==k){
                        //对角线
                        allData[i][j][k] = 1.0;
                    }else {
                        //下三角
                        allData[i][j][k] = 1.0/allData[i][k][j];
                    }

                    //保存初始矩阵 一共存储500条记录
                    //存入数据库
                    matrixStorage.setProjectName(projectName);
                    matrixStorage.setI(j);
                    matrixStorage.setJ(k);
                    matrixStorage.setMatrixValue(allData[i][j][k]);
//                    matrixStorage.setUsername(""); //user先不做设定
                    matrixStorage.setValue(criterionList.get(i));
                    matrixStorageDBService.insOrUpdByMS(matrixStorage);


                }
            }
        }
        //若结论过多，存储的数据量将十分庞大

        //保存初始矩阵 一共存储500条记录

        //一开始有5条10*10
        //归一后得到权重变为五条10*1
        //结合成10*5的矩阵
        //一共要录入五十条数据
        //计算归一权重
        //计算WC
        NormalizationWeight myNW = new NormalizationWeight();
        String nowCriterion = null;
        String nextValue = null;
        NWResult nwResult = null;
        for (int i = 0; i < 5; i++) {
            //这是 value
            nowCriterion = criterionList.get(i);
            //计算权重
            nwResult = this.NWCalculation(allData[i]);
            for (int j = 0; j < 10; j++) {
                //医生名（结论名）就是所需的nextValue
                nextValue = docList.get(j).getDocname();
                myNW.setValue(nowCriterion);
                myNW.setNextValue(nextValue);
                myNW.setProjectName(projectName);
                myNW.setWeight(nwResult.getW()[j]);
                //保存归一化后的权重数据
                normalizationWeightDBService.insOrUpdByNW(myNW);
            }

        }
        //保存归一化后的权重数据


    }
}
