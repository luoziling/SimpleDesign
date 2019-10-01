package com.wzb.businessservice.impl;

import com.netflix.discovery.converters.Auto;
import com.wzb.businessservice.CalculationService;
import com.wzb.businessservice.feignservice.*;
import com.wzb.common.NWResult;
import com.wzb.common.RI;
import com.wzb.pojo.AdjacentClosure;
import com.wzb.pojo.Conclusion;
import com.wzb.pojo.NormalizationWeight;
import com.wzb.pojo.TreeNodeContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Satsuki
 * @time 2019/10/1 19:01
 * @description:
 */
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
}
