
package com.nou.reg.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.acer.apps.Page;
import com.acer.db.DBManager;
import com.acer.db.query.DBResult;
import com.acer.util.Utility;
import com.nou.UtilityX;
import com.nou.aut.AUTCONNECT;
import com.nou.pla.dao.PLAT002DAO;
import com.nou.pla.dao.PLAT012GATEWAY;
import com.nou.pla.dao.PLAT027DAO;
import com.nou.pla.dao.PLAT028DAO;
import com.nou.reg.bo.REGCALCPAYMENT;
import com.nou.sgu.dao.SGUT001DAO;
import com.nou.sys.dao.SYST002DAO;

/*
 * (REGT007) Gateway/*
 *-------------------------------------------------------------------------------*
 * Author    : 國長      2007/05/04
 * Modification Log :
 * Vers     Date           By             Notes
 *--------- -------------- -------------- ----------------------------------------
 * V0.0.1   2007/05/04     國長           建立程式
 *                                        新增 getRegt007ForUse(Hashtable ht)
 * V0.0.2   2007/06/05     sRu            新增 getReg_CRSNO(Hashtable ht)
 * V0.0.3   2007/06/06     sRu            新增 getReg_stu_crs(Hashtable ht)
 * V0.0.4   2007/06/21     芳如             新增 getCnt_CenCrsVoc(Hashtable ht)
				                                          新增 getTotCnt_Cen(Hashtable ht)
				                                          新增 getSumCnt_CenVoc(Hashtable ht)
				                                          新增 getSumCnt_CenEdu(Hashtable ht)
				                                          新增 getCnt_CenCrsEdu(Hashtable ht)
				                                          新增 getTotCnt(Hashtable ht)
				                                          新增 getSumCnt_Cen(Hashtable ht)
				                                          新增 getCnt_CrsCen(Hashtable ht)
				                                          新增 getSumCntForType_Cen(Hashtable ht)
				                                          新增 getCntForType_CenCrs(Hashtable ht)
 * V0.0.5  2007/08/16      立文           新增 getRegt007Stut002Stut003Syst002(Hashtabe ht)
                                               getRegt007Stut003Stut002(Hashtable ht)
 * V0.0.6  2007/08/17      立文           新增 getRegt007Stut003Syst001Syst002ForUse(Hashtable ht)
 * V0.0.7  2007/08/20      立文           新增 getRegt007Plat012ForUse(Hashtable ht)
 * V0.0.8  2007/09/07      ALEX           新增 getRegt007Plat002Print(Hashtable ht)
 * V0.0.9  2007/09/04      立文           新增 getRegt007Cout001ForUse(Hashtable ht)
 * V0.0.10 2007/09/05      立文           新增 getRegt007Stut002Stut003Syst002Syst001(Hashtable ht)
 * V0.0.11 2007/09/05      gtu            新增 getRegt007ForUse_2(Hashtable ht)
 * V0.0.12 2007/09/10      ALEX           新增 getRegt007Plat002Print1(Hashtable ht)
 * V0.0.13 2007/09/12      ALEX           新增 getRegt007Stut003Syst002Print(Hashtable ht)
 * V0.0.14 2007/09/12      ALEX           新增 getRegt007Plat012Print(Hashtable ht)
 * V0.0.15 2007/09/17      ALEX           新增 getRegt007Stut003Stut002Cout001Print(Hashtable ht)
 * V0.0.16 2007/09/18      ALEX           新增 getRegt007Cout001Stut003Cout002Print(Hashtable ht)
 * V0.0.17 2007/09/18      ALEX           新增 getRegt007Stut003Print11
 * V0.0.18 2007/10/01      gtu            新增 getRegt007Count_ForUse(Hashtable ht)
 * V0.0.19 2007/10/05      ALEX           新增 getRegt007Reg108rPrint(Hashtable ht)
 * V0.0.20 2007/10/05      ALEX           新增 getRegt007Reg134rSTNOPrint(Hashtable ht)
 * V0.0.21 2007/10/05      ALEX           新增 getRegt007Reg134rCRSNOPrint(Hashtable ht)
 * V0.0.22 2007/10/08      ALEX           新增 getRegt007Reg106rSTNOPrint(Hashtable ht)
 * V0.0.23 2007/10/12      ALEX           新增 getRegt007Reg111rPrint(Hashtable ht)
 * V0.0.24 2007/10/15      ALEX           新增 getRegt007REG126Print(Hashtable ht)
 * V0.0.15 2007/10/15      ALEX           修改 getRegt007Stut003Stut002Cout001Print(Hashtable ht)
 * V0.0.16 2007/10/15      ALEX           修改 getRegt007Cout001Stut003Cout002Print(Hashtable ht)
 * V0.0.26 2007/10/15      ALEX           修改 getRegt007Reg128rPrint(Hashtable ht)
 * V0.0.22 2007/10/14	   sorge	      新增 getREG139R_REG144Rdata(Vector vt, Hashtable ht)
 * V0.0.22 2007/10/14	   sorge	      新增 getReg145r_Reg152rData(Vector vt, Hashtable ht)
 * V0.0.22 2007/10/14	   sorge	      新增 getReg153r_Reg154rData(Vector vt, Hashtable ht)
 * V0.0.27 2007/10/19      ALEX           新增 getRegt007Reg135rPrint(Hashtable ht)
 * V0.0.28 2007/10/19      ALEX           新增 getRegt007Reg135rPrint1(Hashtable ht)
 * V0.0.29 2007/10/19      ALEX           新增 getRegt007REG103Print(Hashtable ht)
 * V0.0.30 2007/11/21      Tonny           修改getRegt007Stut003Stut002(Hashtable ht)
 * V0.0.31 2007/12/19	   sorge		        新增getAutIndexSResult1(Vector vt, Hashtable ht)
 * V0.0.32 2007/11/21      shony           修改getRegt007Plat012ForUse(Hashtable ht)
 * V0.0.33 2008/01/07      WEN             修改getRegt007Stut003Syst002Print(Hashtable ht)
 * V0.0.34 2008/01/09      WEN              新增getRegt007Stut003forRpt(Hashtable ht)
 *                                          修改getRegt007Plat002Print(Hashtable ht)
 * V0.0.35  2008/02/04     WEN             新增getRegt007Cout001Stut003Scdt008forUse(Hashtable ht)
 * V0.0.35  2008/02/04      葉瑞欽                修改getRegt007Cout001Stut003Cout002Print(Hashtable ht)
 * V0.0.36  2008/02/13      WEN             新增getCout001Stut003Sgut004Regt007forUse(Hashtable ht)
 * V0.0.37  2008/02/13      WEN             新增getRegt007Cout001Stut003Sgut004forUse(Hashtable ht)
 * V0.0.38  2008/02/18      WEN             新增getRegt007Cout001Stut003Scdt008Regt004forUse(Hashtable ht)
 * V0.0.39	2008/02/20		sRu				新增getRegt007Cout001Scdt008(Hashtable ht)
 * 											修改getRegt007Cout001Stut003Scdt008Regt004forUse(Hashtable ht) 增加學號條件
 * V0.0.40  2008/02/25      葉瑞欽        修改 getRegt007Reg108rPrint(Hashtable ht)
 * V0.0.41  2008/02/29      葉瑞欽        修改 getRegt007Reg128rPrint(Hashtable ht)
 * V0.0.42  2008/03/02      WEN        修改 getCout001Stut003Sgut004Regt007forUse(Hashtable ht)
 * V0.0.43  2008/03/19      葉瑞欽        修改 getRegt007Reg135rPrint(Hashtable ht)
 * V0.0.44  2008/03/21      葉瑞欽        修改 getREG139R_REG144Rdata(Vector vt, Hashtable ht)
 * v0.0.45  2008/03/24      葉瑞欽        修改 getRegt007Reg108rPrint(Hashtable ht)
 * v0.0.46	2008/03/25		sRu		修改 getReg153r_Reg154rData(Vector vt, Hashtable ht)REGT007.UNTAKECRS_MK='N'
 * 										getReg145r_Reg152rData(Vector vt, Hashtable ht)REGT007.UNTAKECRS_MK='N'
 * V0.0.47  2008/03/27      葉瑞欽        修改 getRegt007Cout001Stut003Cout002Print(Hashtable ht)
 *                                             getRegt007Stut003Stut002Cout001Print(Hashtable ht)
 *                                             getRegt007REG126Print(Hashtable ht)
 * V0.0.48	2008/04/01		sRu		 修改 getRegt007Reg108rPrint(Hashtable ht)
 * 										getRegt007Count_ForUse(Hashtable ht)
 * V0.0.49	2008/04/12		sRu		修改getReg145r_Reg152rData(Hashtable ht)
 * V0.0.50  2008/04/28      WEN     修改getRegt007Count_ForUse(Hashtable ht)
 * V0.0.51	2008/05/15		sRu		修改getRegt007Stut003Stut002Cout001Print(Hashtable ht)
 *--------------------------------------------------------------------------------
 */

public class REGT007GATEWAY {

    /** 資料排序方式 */
    private String orderBy = "";
    private DBManager dbmanager = null;
    private Connection conn = null;
    /* 頁數 */
    private int pageNo = 0;
    /** 每頁筆數 */
    private int pageSize = 0;

    /** 記錄是否分頁 */
    private boolean pageQuery = false;

    /** 用來存放 SQL 語法的物件 */
    private StringBuffer sql = new StringBuffer();
    private StringBuffer sql2 = new StringBuffer();
    /** <pre>
     *  設定資料排序方式.
     *  Ex: "AYEAR, SMS DESC"
     *      先以 AYEAR 排序再以 SMS 倒序序排序
     *  </pre>
     */
    public void setOrderBy(String orderBy) {
        if(orderBy == null) {
            orderBy = "";
        }
        this.orderBy = orderBy;
    }

    /** 取得總筆數 */
    public int getTotalRowCount() {
        return Page.getTotalRowCount();
    }

    /** 不允許建立空的物件 */
    private REGT007GATEWAY() {}

    /** 建構子，查詢全部資料用 */
    public REGT007GATEWAY(DBManager dbmanager, Connection conn) {
        this.dbmanager = dbmanager;
        this.conn = conn;
    }

    /** 建構子，查詢分頁資料用 */
    public REGT007GATEWAY(DBManager dbmanager, Connection conn, int pageNo, int pageSize) {
        this.dbmanager = dbmanager;
        this.conn = conn;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        pageQuery = true;
    }

    /**
     *
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
     * @throws Exception
     */
    public Vector getRegt007ForUse(Hashtable ht) throws Exception {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        sql.append(
            "SELECT R07.ASYS, R07.AYEAR, R07.SMS, R07.STNO, R07.CRSNO, R07.CRD, R07.TAKE_DATE, R07.TUT_CMPS_CODE, R07.LAB_CMPS_CODE, R07.PRE_LAB_SECTION, R07.CLASS_CODE, R07.TAKE_ABNDN, R07.TAKE_ABNDN_DATE, R07.TAKE_ABNDN_UPD_ID, R07.UNQUAL_TAKE_MK " +
            "FROM REGT007 R07 " +
            "WHERE 1 = 1 "
        );
        if(!Utility.nullToSpace(ht.get("ASYS")).equals("")) {
            sql.append("AND R07.ASYS = '" + Utility.nullToSpace(ht.get("ASYS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND R07.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND R07.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("STNO")).equals("")) {
            sql.append("AND R07.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
            sql.append("AND R07.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRD")).equals("")) {
            sql.append("AND R07.CRD = '" + Utility.nullToSpace(ht.get("CRD")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("TAKE_DATE")).equals("")) {
            sql.append("AND R07.TAKE_DATE = '" + Utility.nullToSpace(ht.get("TAKE_DATE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("TUT_CMPS_CODE")).equals("")) {
            sql.append("AND R07.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("TUT_CMPS_CODE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("LAB_CMPS_CODE")).equals("")) {
            sql.append("AND R07.LAB_CMPS_CODE = '" + Utility.nullToSpace(ht.get("LAB_CMPS_CODE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("PRE_LAB_SECTION")).equals("")) {
            sql.append("AND R07.PRE_LAB_SECTION = '" + Utility.nullToSpace(ht.get("PRE_LAB_SECTION")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CLASS_CODE")).equals("")) {
            sql.append("AND R07.CLASS_CODE = '" + Utility.nullToSpace(ht.get("CLASS_CODE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("TAKE_ABNDN")).equals("")) {
            sql.append("AND R07.TAKE_ABNDN = '" + Utility.nullToSpace(ht.get("TAKE_ABNDN")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("TAKE_ABNDN_DATE")).equals("")) {
            sql.append("AND R07.TAKE_ABNDN_DATE = '" + Utility.nullToSpace(ht.get("TAKE_ABNDN_DATE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("TAKE_ABNDN_UPD_ID")).equals("")) {
            sql.append("AND R07.TAKE_ABNDN_UPD_ID = '" + Utility.nullToSpace(ht.get("TAKE_ABNDN_UPD_ID")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("UNQUAL_TAKE_MK")).equals("")) {
            sql.append("AND R07.UNQUAL_TAKE_MK = '" + Utility.nullToSpace(ht.get("UNQUAL_TAKE_MK")) + "' ");
        }

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "R07." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }


    /**
    *for PLA118R_實習校區選課過濾統計表
    * @param ht 條件值
    * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
    *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
    *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
    * @throws Exception
    */
   public Vector getRegt007Stut003Syst002ForUse(Hashtable ht) throws Exception {
       if(ht == null) {
           ht = new Hashtable();
       }
       Vector result = new Vector();
       if(sql.length() > 0) {
           sql.delete(0, sql.length());
       }
       sql.append(
           " SELECT A.AYEAR,A.SMS,C.CENTER_ABRCODE,A.LAB_CMPS_CODE,A.CRSNO,A.STNO, " +
           "        A.VOLNUTEER_TIME01, A.VOLNUTEER_TIME02, A.VOLNUTEER_TIME03, A.VOLNUTEER_TIME04, A.VOLNUTEER_TIME05,  " +
           "        A.VOLNUTEER_TIME06, A.VOLNUTEER_TIME07, A.VOLNUTEER_TIME08, A.VOLNUTEER_TIME09, A.VOLNUTEER_TIME10 "+
           " FROM REGT007 A  " +
           " JOIN STUT003 B ON A.STNO=B.STNO "+
           " JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE " +
           " WHERE 1 = 1 "
       );

       if(!Utility.nullToSpace(ht.get("AYEAR")).equals("") && !Utility.nullToSpace(ht.get("SMS")).equals("")){
    	   sql.append(
    	       "  AND A.AYEAR='"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS='"+Utility.nullToSpace(ht.get("SMS"))+
    	       "' AND A.CRSNO IN (SELECT CRSNO FROM COUT001 WHERE LAB_TIMES >0 AND  AYEAR='"+Utility.nullToSpace(ht.get("AYEAR"))+
    	       "' AND SMS='"+Utility.nullToSpace(ht.get("SMS"))+"' AND EST_RESULT_MK='Y' ) "
    	   );
       }else {
    	   sql.append(" AND 1 =2 " );

       }
       if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
           sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
       }
       if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
           sql.append("AND A.LAB_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
       }
       if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
           sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
       }

       if(!orderBy.equals("")) {
           String[] orderByArray = orderBy.split(",");
           orderBy = "";
           for(int i = 0; i < orderByArray.length; i++) {
               orderByArray[i] = "R07." + orderByArray[i].trim();

               if(i == 0) {
                   orderBy += "ORDER BY ";
               } else {
                   orderBy += ", ";
               }
               orderBy += orderByArray[i].trim();
           }
           sql.append(orderBy.toUpperCase());
           orderBy = "";
       }
       sql.append( " ORDER BY A.AYEAR,A.SMS,C.CENTER_ABRCODE,A.LAB_CMPS_CODE,A.CRSNO,A.STNO ");
       DBResult rs = null;
       try {
           if(pageQuery) {
               // 依分頁取出資料
               rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
           } else {
               // 取出所有資料
               rs = dbmanager.getSimpleResultSet(conn);
               rs.open();
               rs.executeQuery(sql.toString());
           }
           Hashtable rowHt = null;
           while (rs.next()) {
               rowHt = new Hashtable();
               /** 將欄位抄一份過去 */
               for (int i = 1; i <= rs.getColumnCount(); i++)
                   rowHt.put(rs.getColumnName(i), rs.getString(i));

               result.add(rowHt);
           }
       } catch (Exception e) {
           throw e;
       } finally {
           if(rs != null) {
               rs.close();
           }
       }
       return result;
   }

    /**
     * 列印選課總表 - 取得學生選課科目 (REGT007註冊選課明細、STUT004)
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
     * @throws Exception
     */
    public Vector getReg_CRSNO(Hashtable ht) throws Exception
    {
        Vector      result  =   null;
        DBResult    rs      =   null;

        try
        {
            result  =   new Vector();

            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());

            sql.append
            (
                "SELECT A.* , B.* " +
                "FROM REGT007 A, STUT004 B " +
                "WHERE A.AYEAR = B.AYEAR " +
                "AND A.SMS = B.SMS " +
                "AND A.STNO = B.STNO "

            );

            //條件欄位
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別
            String  STTYPE          =   Utility.checkNull(ht.get("STTYPE"), "");            //身份別


            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND A.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND A.SMS = '" + SMS + "' ");

            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND A.ASYS = '" + ASYS + "' ");

            //加入條件 - 中心別
            if (!"".equals(CENTER_CODE))
                sql.append("AND B.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 身份別
            if (!"".equals(STTYPE))
                sql.append("AND B.STTYPE = '" + STTYPE + "' ");
            else
                sql.append("AND (B.STTYPE = '1' OR B.STTYPE = '2') ");


            //排序
            sql.append("ORDER BY B.STNO, A.CRSNO ");

            //System.out.println("getReg_CRSNO=sql===" + sql.toString());

            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            Hashtable   rowHt   =   null;

            while (rs.next())
            {
                rowHt   =   new Hashtable();

                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }

            return result;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (rs != null)
                rs.close();
        }
    }

    /**
     * 列印學生選課卡 - 取得學生選課科目 (REGT007註冊選課明細、STUT003、COUT018)
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
     * @throws Exception
     */
    public Vector getReg_stu_crs(Hashtable ht) throws Exception
    {
        Vector      result  =   null;
        DBResult    rs      =   null;

        try
        {
            result  =   new Vector();

            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());

            sql.append
            (
                "SELECT A.IDNO, A.BIRTHDATE, A.ASYS, A.CENTER_CODE, A.STNO , B.CRSNO,B.ASGN_CLASS_CODE,B.AYEAR, B.SMS, C.CRD  " +
                "FROM STUT003 A, REGT007 B, COUT018 C " +
                "WHERE 1 = 1 " +
                "AND A.STNO = B.STNO " +
                "AND B.CRSNO = C.CRSNO "  +
                "AND B.AYEAR = C.AYEAR " +
                "AND B.SMS = C.SMS " +
                "AND B.PAYMENT_STATUS = '1' " +
                "AND B.UNTAKECRS_MK <> 'Y' "
            );

            //條件欄位
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別
            String  STTYPE          =   Utility.checkNull(ht.get("STTYPE"), "");            //身份別
            String  STNO            =   Utility.checkNull(ht.get("STNO"), "");              //學號
            String  NAME            =   Utility.checkNull(ht.get("NAME"), "");              //姓名

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND B.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND B.SMS = '" + SMS + "' ");

            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND A.ASYS = '" + ASYS + "' ");

            //加入條件 - 中心別
            if (!"".equals(CENTER_CODE))
                sql.append("AND A.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 身份別
            if (!"".equals(STTYPE))
                sql.append("AND A.STTYPE = '" + STTYPE + "' ");

            //加入條件 - 學號
            if (!"".equals(STNO))
                sql.append("AND A.STNO = '" + STNO + "' ");

            //加入條件 - 姓名
            if (!"".equals(NAME))
                sql.append("AND A.NAME = '" + NAME + "' ");

            //排序
            sql.append("ORDER BY A.STNO, B.CRSNO ");

//            System.out.println("getReg_stu_crs=sql===" + sql.toString());

            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            Hashtable   rowHt   =   null;

            while (rs.next())
            {
                rowHt   =   new Hashtable();

                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }

            return result;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if (rs != null)
                rs.close();
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別 & 科目別 & 職業別" 取得人數 (REGT007, STUT002, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getCnt_CenCrsVoc(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");             //科目別代碼
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別代碼
            String  VOCATION        =   Utility.checkNull(ht.get("VOCATION"), "");          //職業別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT002, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append
            (
                "REGT007.STNO = STUT003.STNO AND " +
                "STUT003.IDNO = STUT002.IDNO AND " +
                "STUT003.BIRTHDATE = STUT002.BIRTHDATE "
            );


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO = '" + CRSNO + "' ");

            //加入條件 - 中心別代碼
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 職業別代碼
            if (!"".equals(VOCATION))
                sql.append("AND STUT002.VOCATION = '" + VOCATION + "' ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別" 取得人數 (REGT007, STUT002, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getTotCnt_Cen(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");             //科目別代碼
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT002, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append
            (
                "REGT007.STNO = STUT003.STNO AND " +
                "STUT003.IDNO = STUT002.IDNO AND " +
                "STUT003.BIRTHDATE = STUT002.BIRTHDATE "
            );


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心別代碼
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別 & 職業別" 取得人數 (REGT007, STUT002, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getSumCnt_CenVoc(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");             //科目別代碼
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別代碼
            String  VOCATION        =   Utility.checkNull(ht.get("VOCATION"), "");          //職業別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT002, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append
            (
                "REGT007.STNO = STUT003.STNO AND " +
                "STUT003.IDNO = STUT002.IDNO AND " +
                "STUT003.BIRTHDATE = STUT002.BIRTHDATE "
            );


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心別代碼
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 職業別代碼
            if (!"".equals(VOCATION))
                sql.append("AND STUT002.VOCATION = '" + VOCATION + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別 & 學歷別" 取得人數 (REGT007, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getSumCnt_CenEdu(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");             //科目別代碼
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別代碼
            String  EDUBKGRD        =   Utility.checkNull(ht.get("EDUBKGRD"), "");          //學歷別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append("REGT007.STNO = STUT003.STNO ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心別代碼
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 學歷別代碼
            if (!"".equals(EDUBKGRD))
                sql.append("AND STUT003.EDUBKGRD = '" + EDUBKGRD + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別 & 科目別 & 學歷別" 取得人數 (REGT007, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getCnt_CenCrsEdu(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");              //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");             //科目別代碼
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");       //中心別代碼
            String  EDUBKGRD        =   Utility.checkNull(ht.get("EDUBKGRD"), "");          //學歷別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append("REGT007.STNO = STUT003.STNO ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO = '" + CRSNO + "' ");

            //加入條件 - 中心別代碼
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 學歷別代碼
            if (!"".equals(EDUBKGRD))
                sql.append("AND STUT003.EDUBKGRD = '" + EDUBKGRD + "' ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 取得總人數 (REGT007, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getTotCnt(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS    =   Utility.checkNull(ht.get("ASYS"), "");      //學制
            String  AYEAR   =   Utility.checkNull(ht.get("AYEAR"), "");     //學年
            String  SMS     =   Utility.checkNull(ht.get("SMS"), "");       //學期
            String  CRSNO   =   Utility.checkNull(ht.get("CRSNO"), "");     //科目別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append("REGT007.STNO = STUT003.STNO ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "中心別" 取得人數 (REGT007, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getSumCnt_Cen(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");          //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");         //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");           //學期
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");   //中心別
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");         //科目別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append("REGT007.STNO = STUT003.STNO ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 依 "科目別 & 中心別" 取得人數 (REGT007, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getCnt_CrsCen(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");          //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");         //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");           //學期
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");   //中心別
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");         //科目別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT COUNT('1') CNT " +
                "FROM REGT007, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append("REGT007.STNO = STUT003.STNO ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO = '" + CRSNO + "' ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 取得此中心所有科目別的所有學生 (REGT007, STUT002, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getSumCntForType_Cen(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");          //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");         //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");           //學期
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");   //中心別
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");         //科目別範圍


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT STUT003.STTYPE, STUT002.SEX, STUT002.BIRTHDATE " +
                "FROM REGT007, STUT002, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append
            (
                "REGT007.STNO = STUT003.STNO AND " +
                "STUT003.IDNO = STUT002.IDNO AND " +
                "STUT003.BIRTHDATE = STUT002.BIRTHDATE "
            );


            //限制身份別只能為全修及選修
            sql.append("AND STUT003.STTYPE IN ('1', '2') ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO IN (" + CRSNO + ") ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 各科目學生基本資料統計表 - 取得此中心此科目別的各種類學生 (REGT007, STUT002, STUT003)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public DBResult getCntForType_CenCrs(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  ASYS            =   Utility.checkNull(ht.get("ASYS"), "");          //學制
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");         //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");           //學期
            String  CENTER_CODE     =   Utility.checkNull(ht.get("CENTER_CODE"), "");   //中心別
            String  CRSNO           =   Utility.checkNull(ht.get("CRSNO"), "");         //科目別代碼


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT STUT003.STTYPE, STUT002.SEX, STUT002.BIRTHDATE " +
                "FROM REGT007, STUT002, STUT003 " +
                "WHERE "
            );


            //JOIN 條件
            sql.append
            (
                "REGT007.STNO = STUT003.STNO AND " +
                "STUT003.IDNO = STUT002.IDNO AND " +
                "STUT003.BIRTHDATE = STUT002.BIRTHDATE "
            );


            //限制身份別只能為全修及選修
            sql.append("AND STUT003.STTYPE IN ('1', '2') ");


            //加入條件 - 學制
            if (!"".equals(ASYS))
                sql.append("AND REGT007.ASYS = '" + ASYS + "' ");

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND REGT007.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND REGT007.SMS = '" + SMS + "' ");

            //加入條件 - 中心
            if (!"".equals(CENTER_CODE))
                sql.append("AND STUT003.CENTER_CODE = '" + CENTER_CODE + "' ");

            //加入條件 - 科目別代碼
            if (!"".equals(CRSNO))
                sql.append("AND REGT007.CRSNO = '" + CRSNO + "' ");


            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


//            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut002Stut003Syst002(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
                    "SELECT DISTINCT A.AYEAR, A.SMS, A.STNO, A.ALLOCATE_CMPS_CODE, C.CENTER_NAME, D.NAME   " +
                    "FROM REGT007 A " +
                    	"JOIN STUT003 B ON A.STNO = B.STNO " +
                    	"JOIN SYST002 C ON B.CENTER_CODE = C.CENTER_CODE " +
                    	"JOIN STUT002 D ON B.IDNO = D.IDNO AND B.BIRTHDATE = D.BIRTHDATE "
                      );

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
				sql.append("JOIN plat002 e ON a.ayear = e.ayear AND a.sms = e.sms AND c.center_abrcode = e.center_abrcode AND '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' = e.cmps_code ");

			sql.append(
				"WHERE " +
					"     A.UNQUAL_TAKE_MK = 'N' "+
		    		" AND A.UNTAKECRS_MK = 'N' "+
		    		" AND A.PAYMENT_STATUS <> '1' "
		    );

			if(!Utility.nullToSpace(ht.get("STNO")).equals(""))
				sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
			if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
				sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
			if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
				sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
			if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
				sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
				sql.append("AND DECODE (e.cmps_kind, '1', a.tut_cmps_code, '2', a.tut_cmps_code, '3', a.lab_cmps_code) = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");

			if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
				sql.append(" AND A.PAYMENT_STATUS <> '1' ");

        //== 查詢條件 ED ==

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "SOLT001." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }
//System.out.println("pla004m="+sql.toString());
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

	//專給PLA004M查詢使用  2008/12/05   by barry
	  /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut002Stut003Syst002_2(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
                    "SELECT DISTINCT A.AYEAR, A.SMS, A.STNO, A.ALLOCATE_CMPS_CODE, C.CENTER_NAME, D.NAME   " +
                    "FROM REGT007 A " +
                    	"JOIN STUT003 B ON A.STNO = B.STNO " +
                    	"JOIN SYST002 C ON B.CENTER_CODE = C.CENTER_CODE " +
                    	"JOIN STUT002 D ON B.IDNO = D.IDNO AND B.BIRTHDATE = D.BIRTHDATE "
                      );

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
				sql.append("JOIN plat002 e ON a.ayear = e.ayear AND a.sms = e.sms AND c.center_abrcode = e.center_abrcode AND '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' = e.cmps_code ");

			sql.append(
				"WHERE " +
					"     A.UNQUAL_TAKE_MK = 'N' "+
		    		" AND A.UNTAKECRS_MK = 'N' "
		    );

			if(!Utility.nullToSpace(ht.get("STNO")).equals(""))
				sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
			if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
				sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
			if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
				sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
			if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
				sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
				sql.append("AND DECODE (e.cmps_kind, '1', a.tut_cmps_code, '2', a.tut_cmps_code, '3', a.lab_cmps_code) = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");

			if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
				sql.append(" AND A.PAYMENT_STATUS <> '1' ");

        //== 查詢條件 ED ==

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "SOLT001." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }
//System.out.println("pla004m="+sql.toString());
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

    /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Cout002ForUse(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
                    "SELECT A.MASTER_CLASS_CODE,B.CRS_NAME AS CCRSNO,B.CRSNO,A.TUT_CLASS_CODE,A.LAB_CLASS_CODE FROM REGT007 A JOIN COUT002 B ON A.CRSNO = B.CRSNO WHERE 1 = 1  "
                    //AND A.AYEAR = '095'
                    //AND A. SMS = '1'
                    //AND A.STNO = '123456789'
                       );
                         if(!Utility.nullToSpace(ht.get("STNO")).equals("")) {
                         sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
                         }


                        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
                            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
                        }
                        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
                            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
                        }

                        // 退選註記 2008.09.25 north----這裡僅顯示沒有退選的(因有退選時,此欄位為Y)
                        sql.append("AND A.UNTAKECRS_MK = 'N' ");

        //== 查詢條件 ED ==

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "SOLT001." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }


      /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003Stut002(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

	sql.append
	(
        		"SELECT C.TEL_OFFICE||DECODE(c.TEL_OFFICE_EXT,'','','#'||c.TEL_OFFICE_EXT) as OFFICE_TEL, " +
        		"E.CODE_NAME AS CCLASS_ST_DUTY, " +
        		"A.STNO, " +
				"A.ASS_CLASS_CODE, " +
				"A.TUT_CLASS_CODE, " +
				"A.MASTER_CLASS_CODE, " +
        		"C.NAME AS CSTNO, " +
        		"F.CODE_NAME AS CSEX, " +
        		"C.TEL_HOME, " +
        		"C.MOBILE, " +
        		"C.EMAIL, " +
        		"G.CENTER_ABBRNAME " +
        		"FROM REGT007 A " +
        		"	JOIN STUT003 B ON A.STNO=B.STNO " +
        		"	JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE " +
        		"	LEFT JOIN PLAT019 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND A.MASTER_CLASS_CODE=D.CLASS_CODE AND A.STNO=D.STNO " +
        		"	LEFT JOIN SYST001 E ON D.CLASS_ST_DUTY=E.CODE AND E.KIND='CLASS_ST_DUTY' " +
        		"	LEFT JOIN SYST001 F ON C.SEX=F.CODE AND F.KIND='SEX' " +
        		"	JOIN SYST002 G ON G.CENTER_CODE=B.CENTER_CODE " +
        		"WHERE 1=1 "
	);


        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CLASS_CODE")).equals("")) {
        	String classCode = Utility.nullToSpace(ht.get("CLASS_CODE"));
        	classCode=classCode.replaceAll("%40", "@");

        	// 如由教師首頁連結過來時,且為網路班級時,即@,則傳過來的值會變成%40,故要在這轉成@
			if(Utility.nullToSpace(ht.get("CLASS_KIND")).equals("7"))
				sql.append("AND A.LAB_CLASS_CODE = '" + classCode + "' ");
			// 如由教師首頁連結過來時,且為實習班級時,即@,則傳過來的值會變成%40,故要在這轉成@
			if(Utility.nullToSpace(ht.get("CLASS_KIND")).equals("1") || Utility.nullToSpace(ht.get("CLASS_KIND")).equals("8"))
				sql.append("AND A.TUT_CLASS_CODE = '" + classCode +"' ");
			if(Utility.nullToSpace(ht.get("CLASS_KIND")).equals("2") || Utility.nullToSpace(ht.get("CLASS_KIND")).equals("3") || Utility.nullToSpace(ht.get("CLASS_KIND")).equals("4"))
				sql.append("AND A.ASS_CLASS_CODE = '" + classCode + "' ");
			
        }
		if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
            sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
        }
		if(!Utility.nullToSpace(ht.get("CRSNO_T")).equals("")) {
            sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO_T")) + "' ");
        }

		sql.append("AND A.UNQUAL_TAKE_MK = 'N' ");
		sql.append("AND A.UNTAKECRS_MK = 'N' ");
		sql.append("AND A.PAYMENT_STATUS <> '1' ");
		sql.append("ORDER BY B.CENTER_CODE, A.STNO ");

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003Syst001Syst002ForUse(Hashtable ht) throws Exception {
    	 Vector result = new Vector();

    	 // 取得查詢條件校區類型
    	 PLAT002DAO plat002 = new PLAT002DAO(dbmanager, conn);
    	 plat002.setResultColumn("CMPS_KIND");
    	 plat002.setAYEAR(ht.get("AYEAR").toString());
    	 plat002.setSMS(ht.get("SMS").toString());
    	 plat002.setCENTER_ABRCODE(ht.get("CENTER_ABRCODE").toString());
    	 plat002.setCMPS_CODE(ht.get("CMPS_CODE").toString());
    	 DBResult rs = plat002.query();

    	 String classType = "TUT_CMPS_CODE";  // 用來對應PLAT012
    	 if(rs.next())
    		 classType = rs.getString("CMPS_KIND").equals("3")?"LAB_CMPS_CODE":classType;
    	 rs.close();

		if(sql.length() > 0)
			sql.delete(0, sql.length());

		sql.append
		(
			"SELECT NVL(S04.CENTER_CODE,B.CENTER_CODE) as CENTER_CODE, A.TUT_CMPS_CODE, I.CENTER_ABBRNAME AS CCENTER_ABRCODE,A.TUT_CLASS_CODE AS CLASS_CODE, " +
			"(SELECT Y.CMPS_NAME FROM PLAT002 Y WHERE Y.AYEAR=D.AYEAR AND Y.SMS=D.SMS AND Y.CENTER_ABRCODE=I.CENTER_ABRCODE AND Y.CMPS_CODE = A.TUT_CMPS_CODE) AS CCMPS_CODE, " +
			"F.CODE_NAME AS CCLASS_KIND , C.NAME AS CSTNO, SUBSTR(G.CRS_NAME,1,4) AS CCRSNO, A.STNO, J.CLSSRM_NAME,  " +
			"A.ASS_CLASS_CODE, H.NAME AS CTCH_IDNO, D.CLSSRM_CODE, A.CRSNO, D.CLASS_KIND " +
			"FROM REGT007 A " +
			"	JOIN STUT003 B ON A.STNO=B.STNO " +
			"LEFT JOIN STUT004 S04 ON S04.AYEAR = A.AYEAR AND S04.SMS = A.SMS AND S04.STNO = A.STNO "+
			"	JOIN SYST002 I ON I.CENTER_CODE = NVL(S04.CENTER_CODE,b.CENTER_CODE) " +
			"	JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE " 
		);
		sql.append(
		//	"	JOIN PLAT012 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND A.CRSNO=D.CRSNO AND A.ASS_CLASS_CODE=D.CLASS_CODE AND D.CENTER_ABRCODE=I.CENTER_ABRCODE AND D.CMPS_CODE=A."+classType+" " +
			"	JOIN PLAT012 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND A.CRSNO=D.CRSNO "
		);
		
		if (!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			sql.append("AND D.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) +"' ");
		else
			sql.append("AND D.CENTER_ABRCODE = I.CENTER_ABRCODE ");
		
		sql.append("AND D.CMPS_CODE like '"+Utility.nullToSpace(ht.get("CMPS_CODE"))+"%' "+
			"	JOIN PLAT002 E ON D.AYEAR=E.AYEAR AND D.SMS=E.SMS AND D.CENTER_ABRCODE=E.CENTER_ABRCODE AND D.CMPS_CODE=E.CMPS_CODE " +
			"	JOIN SYST001 F ON D.CLASS_KIND=F.CODE AND F.KIND='CLASS_KIND' " +
			"	JOIN COUT002 G ON A.CRSNO=G.CRSNO " +
			"	LEFT JOIN TRAT001 H ON D.TCH_IDNO=H.IDNO " +
			"   LEFT JOIN PLAT003 J ON J.AYEAR=D.AYEAR AND J.SMS=D.SMS AND J.CENTER_ABRCODE=D.CENTER_ABRCODE AND J.CMPS_CODE=D.CMPS_CODE AND J.CLSSRM_CODE=D.CLSSRM_CODE "+
			"WHERE 1=1 "
		);

		if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
		if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
	//	if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
	//		sql.append("AND D.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
	//	if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
	//		sql.append("AND D.CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
		if(!Utility.nullToSpace(ht.get("CLSSRM_CODE")).equals(""))
			sql.append("AND D.CLSSRM_CODE = '" + Utility.nullToSpace(ht.get("CLSSRM_CODE")) + "' ");
	//	if(!Utility.nullToSpace(ht.get("CLASS_CODE")).equals(""))
	//		sql.append("AND A." + (classType.equals("TUT_CMPS_CODE")?"TUT_CLASS_CODE":"LAB_CLASS_CODE") + " = '" + Utility.nullToSpace(ht.get("CLASS_CODE")) + "' ");
		if(!Utility.nullToSpace(ht.get("CRSNO")).equals(""))
			sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
		if(!Utility.nullToSpace(ht.get("STNO")).equals(""))
			sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");

		// 班級條件
		sql.append(
			"AND (    " +
				// 網路面授/遠距面授時,需找出所有該班的學生(散佈在各中心)
			"         ((D.CLASS_KIND='2' OR D.CLASS_KIND='4') AND A.ASS_CLASS_CODE like '"+ht.get("CLASS_CODE")+"%' AND A.ASS_CLASS_CODE=D.CLASS_CODE) "+
				// 其他面授類別,如有輸入中心校區時,僅找到該中心校區(從STU/REG下條件,因要找出該中心的學生,而不是從PLA下中心校區條件)的,而不去找是否有人委任過來該班的人
			"      OR (D.CLASS_KIND!='2' AND D.CLASS_KIND!='4' AND I.CENTER_ABRCODE like '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "%' AND A.TUT_CMPS_CODE like '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "%'  AND A.TUT_CLASS_CODE like '"+ht.get("CLASS_CODE")+"%' AND A.TUT_CLASS_CODE=D.CLASS_CODE) "+
			"    ) "
		);

		sql.append("AND A.UNQUAL_TAKE_MK = 'N' ");
		sql.append("AND A.UNTAKECRS_MK = 'N' ");
		sql.append("AND A.PAYMENT_STATUS <> '1' ");
		//sql.append("AND ((A.TUT_CLASS_CODE = A.ASS_CLASS_CODE AND D.CLASS_KIND = '1') OR (A.TUT_CLASS_CODE <> A.ASS_CLASS_CODE AND D.CLASS_KIND <> '1')) ");
		sql.append("ORDER BY NVL(S04.CENTER_CODE,B.CENTER_CODE), A.TUT_CMPS_CODE, A.STNO, A.CRSNO");

		System.out.println("SQL="+sql.toString());
		try
		{
			if(pageQuery)
			{
				// 依分頁取出資料
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			}
			else
			{
				// 取出所有資料
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;

			while (rs.next())
			{
				rowHt = new Hashtable();
				/** 將欄位抄一份過去 */
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));

				result.add(rowHt);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)
			{
	                		rs.close();
	            	}
		}

		return result;
     }

     /**
 	 * JOIN 其它 TABLE 將欄位的值抓出來
 	 *
 	 * @param ht 條件值
 	 * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
 	 *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
 	 * @throws Exception
 	 */
 	public Vector getRegt007Plat012ForUse(Hashtable ht) throws Exception {
 		Vector result = new Vector();

 		if (sql.length() > 0)
 			sql.delete(0, sql.length());

 		sql.append("SELECT i.cmps_name as cmps_name_lab,h.cmps_name,g.cmps_code, A.AYEAR,A.SMS,A.STNO,D.NAME AS CSTNO,A.CRSNO,B.CRS_NAME AS CCRSNO,A.TUT_CLASS_CODE,A.LAB_CLASS_CODE ");
 		sql.append(", E.CENTER_ABRCODE, A.MASTER_CLASS_CODE, E.CENTER_NAME, A.LAB_CMPS_CODE, A.ASS_CLASS_CODE,C1.LAB_TIMES,NVL(A.S_CLASS_TYPE,'') AS S_CLASS_TYPE ");
 		sql.append(",NVL((select X.STNO from SCDT004 X where X.AYEAR = A.AYEAR AND X.SMS = A.SMS AND X.CRSNO = A.CRSNO AND X.STNO = A.STNO AND NVL(X.RMK1,' ') NOT LIKE '%退%' ),'') AS SCDT004_STNO ");
 		sql.append("FROM REGT007 A ");
 		sql.append("JOIN COUT001 C1 ON C1.AYEAR = A.AYEAR AND C1.SMS = A.SMS AND C1.CRSNO = A.CRSNO ");
 		sql.append("JOIN COUT002 B ON A.CRSNO=B.CRSNO ");
 		sql.append("JOIN STUT003 C ON A.STNO=C.STNO ");
 		sql.append("JOIN STUT002 D ON C.IDNO=D.IDNO AND C.BIRTHDATE=D.BIRTHDATE ");
 		sql.append("LEFT JOIN STUT004 S04 ON S04.AYEAR = A.AYEAR AND S04.SMS = A.SMS AND S04.STNO = A.STNO ");
 		sql.append("JOIN SYST002 E ON E.CENTER_CODE = NVL(S04.CENTER_CODE,C.CENTER_CODE) ");
		//sql.append("LEFT JOIN plat012 f ON a.ayear = f.ayear AND a.sms = f.sms AND a.master_class_code = f.class_code ");
		sql.append("LEFT JOIN (select distinct AYEAR,SMS,CRSNO,CLASS_CODE,CENTER_ABRCODE,CMPS_CODE from PLAT012 GROUP BY AYEAR, SMS, CRSNO, CLASS_CODE,CENTER_ABRCODE,CMPS_CODE) f  ON a.ayear = f.ayear AND a.sms = f.sms AND a.master_class_code = f.class_code AND e.CENTER_ABRCODE = f.CENTER_ABRCODE and A.crsno = f.crsno and a.tut_cmps_code=f.cmps_code ");
		sql.append("LEFT JOIN plat002 g ON a.ayear = g.ayear AND a.sms = g.sms AND f.center_abrcode = g.center_abrcode AND f.cmps_code = g.cmps_code ");
		sql.append("LEFT JOIN PLAT002 H ON A.AYEAR = H.AYEAR AND A.SMS = H.SMS AND E.CENTER_ABRCODE = H.CENTER_ABRCODE AND A.TUT_CMPS_CODE = H.CMPS_CODE ");
		sql.append("LEFT JOIN PLAT002 i ON A.AYEAR = i.AYEAR AND A.SMS = i.SMS AND E.CENTER_ABRCODE = i.CENTER_ABRCODE AND A.LAB_CMPS_CODE = i.CMPS_CODE ");
 		//原條件先註記掉，改成不下PAYMENT_STATUS  2008/08/18  by barry
		//sql.append("WHERE UNQUAL_TAKE_MK='N' AND UNTAKECRS_MK='N' AND PAYMENT_STATUS='2' ");
		//sql.append("WHERE A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' AND A.S_CLASS_TYPE IS NULL ");
		sql.append("WHERE A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' ");
		if (!Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("")){
			if("Y".equals((String)ht.get("PAYMENT_STATUS"))){
				sql.append("AND A.PAYMENT_STATUS>'1'	");
			}else if("N".equals((String)ht.get("PAYMENT_STATUS"))){
				//不下條件
			}
		}	
 		if (!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
 			sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
 		if (!Utility.nullToSpace(ht.get("SMS")).equals(""))
 			sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
 		if (!Utility.nullToSpace(ht.get("CRSNO")).equals(""))
 			sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
 		if (!Utility.nullToSpace(ht.get("STNO")).equals(""))
 			sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
 		if (!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
 			sql.append("AND E.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");

 		// == 查詢條件 ED ==
 		if (!orderBy.equals("")) {
 			String[] orderByArray = orderBy.split(",");
 			orderBy = "";
 			for (int i = 0; i < orderByArray.length; i++) {
 				orderByArray[i] = "SOLT001." + orderByArray[i].trim();

 				if (i == 0) {
 					orderBy += "ORDER BY ";
 				} else {
 					orderBy += ", ";
 				}
 				orderBy += orderByArray[i].trim();
 			}
 			sql.append(orderBy.toUpperCase());
 			orderBy = "";
 		}

 		DBResult rs = null;
 		try {
 			if (pageQuery) {
 				// 依分頁取出資料
 				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
 			} else {
 				// 取出所有資料
 				rs = dbmanager.getSimpleResultSet(conn);
 				rs.open();
 				rs.executeQuery(sql.toString());
 			}
 			Hashtable rowHt = null;
 			while (rs.next()) {
 				rowHt = new Hashtable();
 				/** 將欄位抄一份過去 */
 				for (int i = 1; i <= rs.getColumnCount(); i++) {
 					rowHt.put(rs.getColumnName(i), rs.getString(i));
// 					System.out.print(rs.getString(i));
 				}
 				result.add(rowHt);
 			}
 		} catch (Exception e) {
 			throw e;
 		} finally {
 			if (rs != null) {
 				rs.close();
 			}
 		}
 		return result;
 	}


        /**
     * PLA112R 各校區選課未逾10人統計表
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Plat002Print(Hashtable ht) throws Exception {

    	 Vector result = new Vector();

    	 if(sql.length() > 0) {
    		 sql.delete(0, sql.length());
    	 }

    	 sql.append("select * from ( ");
    	 sql.append("SELECT A.AYEAR, A.SMS, F.CODE_NAME AS SMS_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME AS CENTER_NAME , ");
    	 sql.append("A.TUT_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y AS NUM, E.CRS_NAME, A.CRSNO, COUNT(1) AS TOTAL ");
    	 sql.append("FROM REGT007 A, STUT003 B, SYST002 C, PLAT002 D, COUT002 E, SYST001 F, REGT013 G ");
    	 sql.append("WHERE 0=0 ");
    	 sql.append("AND A.STNO=B.STNO ");
    	 sql.append("AND C.CENTER_CODE=B.CENTER_CODE ");
    	 sql.append("AND A.AYEAR=D.AYEAR ");
    	 sql.append("AND A.SMS=D.SMS ");
    	 sql.append("AND C.CENTER_ABRCODE=D.CENTER_ABRCODE ");
    	 sql.append("AND A.TUT_CMPS_CODE=D.CMPS_CODE ");
    	 sql.append("AND A.CRSNO=E.CRSNO ");
    	 sql.append("AND F.KIND='SMS' ");
    	 sql.append("AND F.CODE=A.SMS ");
    	 sql.append("AND A.AYEAR=G.AYEAR ");
    	 sql.append("AND A.SMS=G.SMS ");
    	 sql.append("AND A.CRSNO=G.CRSNO ");
    	 sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
    	 sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
    	 sql.append("AND A.UNQUAL_TAKE_MK='N' ");
    	 sql.append("AND A.UNTAKECRS_MK='N' ");

    	 if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
    		 sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
    	 }
    	 if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
    		 sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
    	 }
    	 // 表示不含未繳費
		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
			sql.append("AND A.PAYMENT_STATUS > '1' ");

    	 sql.append("GROUP BY A.AYEAR, A.SMS, F.CODE_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME, A.TUT_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y, E.CRS_NAME, A.CRSNO ");
    	 sql.append("HAVING COUNT(1) < " + UtilityX.checkNull(ht.get("NUMBER")) + " ");
    	 sql.append("UNION ");
    	 sql.append("SELECT A.AYEAR, A.SMS, F.CODE_NAME AS SMS_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME AS CENTER_NAME , ");
    	 sql.append("A.LAB_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y AS NUM, E.CRS_NAME, A.CRSNO, COUNT(1) AS TOTAL ");
    	 sql.append("FROM REGT007 A, STUT003 B, SYST002 C, PLAT002 D, COUT002 E, SYST001 F, REGT013 G ");
    	 sql.append("WHERE 0=0 ");
    	 sql.append("AND A.STNO=B.STNO ");
    	 sql.append("AND C.CENTER_CODE=B.CENTER_CODE ");
    	 sql.append("AND A.AYEAR=D.AYEAR ");
    	 sql.append("AND A.SMS=D.SMS ");
    	 sql.append("AND C.CENTER_ABRCODE=D.CENTER_ABRCODE ");
    	 sql.append("AND A.LAB_CMPS_CODE=D.CMPS_CODE ");
    	 sql.append("AND A.CRSNO=E.CRSNO ");
    	 sql.append("AND F.KIND='SMS' ");
    	 sql.append("AND F.CODE=A.SMS ");
    	 sql.append("AND A.AYEAR=G.AYEAR ");
    	 sql.append("AND A.SMS=G.SMS ");
    	 sql.append("AND A.CRSNO=G.CRSNO ");
    	 sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
    	 sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
    	 sql.append("AND A.UNQUAL_TAKE_MK='N' ");
    	 sql.append("AND A.UNTAKECRS_MK='N' ");

    	 if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
    		 sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
    	 }
    	 if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
    		 sql.append("AND A.LAB_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
    	 }
    	 // 表示不含未繳費
 		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
 			sql.append("AND A.PAYMENT_STATUS > '1' ");

    	 sql.append("GROUP BY A.AYEAR, A.SMS, F.CODE_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME, A.LAB_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y, E.CRS_NAME, A.CRSNO ");
    	 sql.append("HAVING COUNT(1) < " + UtilityX.checkNull(ht.get("NUMBER")) + " ");
    	 sql.append(") a ");
    	 sql.append("ORDER BY A.AYEAR, A.SMS, a.CENTER_ABRCODE, a.TUT_CMPS_CODE, A.NUM, A.CRSNO");

    	 System.out.println("getRegt007Plat002Print_1:"+sql.toString());

    	 DBResult rs = null;
        try {
        	// 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            Hashtable   rowHt   =   null;

            while (rs.next())
            {
                rowHt   =   new Hashtable();

                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }

            return result;

        }
        catch(Exception e)
        {
            throw e;
        }
    }
     /**
     * PLA113R PLA113R_各校區選課未逾10人學生名冊
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003forRpt(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {   Vector result = new Vector();
            //條件欄位


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


             sql.append("select ayear, sms, center_abrcode, crsno, total, cmps_name, tut_cmps_code as cmps_code from ( ");
	       	 sql.append("SELECT A.AYEAR, A.SMS, F.CODE_NAME AS SMS_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME AS CENTER_NAME , ");
	       	 sql.append("A.TUT_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y AS NUM, E.CRS_NAME, A.CRSNO, COUNT(1) AS TOTAL ");
	       	 sql.append("FROM REGT007 A, STUT003 B, SYST002 C, PLAT002 D, COUT002 E, SYST001 F, REGT013 G ");
	       	 sql.append("WHERE 0=0 ");
	       	 sql.append("AND A.STNO=B.STNO ");
	       	 sql.append("AND C.CENTER_CODE=B.CENTER_CODE ");
	       	 sql.append("AND A.AYEAR=D.AYEAR ");
	       	 sql.append("AND A.SMS=D.SMS ");
	       	 sql.append("AND C.CENTER_ABRCODE=D.CENTER_ABRCODE ");
	       	 sql.append("AND A.TUT_CMPS_CODE=D.CMPS_CODE ");
	       	 sql.append("AND A.CRSNO=E.CRSNO ");
	       	 sql.append("AND F.KIND='SMS' ");
	       	 sql.append("AND F.CODE=A.SMS ");
	       	 sql.append("AND A.AYEAR=G.AYEAR ");
	       	 sql.append("AND A.SMS=G.SMS ");
	       	 sql.append("AND A.CRSNO=G.CRSNO ");
	       	 sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
	       	 sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
	       	 sql.append("AND A.UNQUAL_TAKE_MK='N' ");
	    	 sql.append("AND A.UNTAKECRS_MK='N' ");

	       	 if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
	       		 sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
	       	 }
	       	 if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
	       		 sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
	       	 }
	       	 // 表示不含未繳費
	 		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
	 			sql.append("AND A.PAYMENT_STATUS > '1' ");

	       	 sql.append("GROUP BY A.AYEAR, A.SMS, F.CODE_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME, A.TUT_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y, E.CRS_NAME, A.CRSNO ");
	       	 sql.append("HAVING COUNT(1) < " + UtilityX.checkNull(ht.get("NUMBER")) + " ");
	       	 sql.append("UNION ");
	       	 sql.append("SELECT A.AYEAR, A.SMS, F.CODE_NAME AS SMS_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME AS CENTER_NAME , ");
	       	 sql.append("A.LAB_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y AS NUM, E.CRS_NAME, A.CRSNO, COUNT(1) AS TOTAL ");
	       	 sql.append("FROM REGT007 A, STUT003 B, SYST002 C, PLAT002 D, COUT002 E, SYST001 F, REGT013 G ");
	       	 sql.append("WHERE 0=0 ");
	       	 sql.append("AND A.STNO=B.STNO ");
	       	 sql.append("AND C.CENTER_CODE=B.CENTER_CODE ");
	       	 sql.append("AND A.AYEAR=D.AYEAR ");
	       	 sql.append("AND A.SMS=D.SMS ");
	       	 sql.append("AND C.CENTER_ABRCODE=D.CENTER_ABRCODE ");
	       	 sql.append("AND A.LAB_CMPS_CODE=D.CMPS_CODE ");
	       	 sql.append("AND A.CRSNO=E.CRSNO ");
	       	 sql.append("AND F.KIND='SMS' ");
	       	 sql.append("AND F.CODE=A.SMS ");
	       	 sql.append("AND A.AYEAR=G.AYEAR ");
	       	 sql.append("AND A.SMS=G.SMS ");
	       	 sql.append("AND A.CRSNO=G.CRSNO ");
	       	 sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
	       	 sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
	       	 sql.append("AND A.UNQUAL_TAKE_MK='N' ");
	    	 sql.append("AND A.UNTAKECRS_MK='N' ");

	       	 if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
	       		 sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
	       	 }
	       	 if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
	       		 sql.append("AND A.LAB_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
	       	 }
	       	 // 表示不含未繳費
		 	if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
		 		sql.append("AND A.PAYMENT_STATUS > '1' ");
		
	       	 sql.append("GROUP BY A.AYEAR, A.SMS, F.CODE_NAME, C.CENTER_ABRCODE, C.CENTER_ABBRNAME, A.LAB_CMPS_CODE, D.CMPS_NAME, G.LOC_X||G.LOC_Y, E.CRS_NAME, A.CRSNO ");
	       	 sql.append("HAVING COUNT(1) < " + UtilityX.checkNull(ht.get("NUMBER")) + " ");
	       	 sql.append(") a ");
	       	 sql.append("ORDER BY A.AYEAR, A.SMS, a.CENTER_ABRCODE, a.TUT_CMPS_CODE, A.NUM, A.CRSNO");

            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            Hashtable   rowHt   =   null;

            while (rs.next())
            {
                rowHt   =   new Hashtable();

                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }

            return result;

        }
        catch(Exception e)
        {
            throw e;
        }
    }
           /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Cout001ForUse(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
                  "SELECT B.REQOPTION_TYPE, A.STNO, B.CRD, A.MASTER_CLASS_CODE, A.CRSNO  FROM REGT007 A " +
                  "JOIN COUT001 B ON A.AYEAR = B.AYEAR AND A.SMS = B.SMS AND A.CRSNO = B.CRSNO " +
                  "WHERE 1 = 1 "
                  );
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        if(!Utility.nullToSpace(ht.get("STNO")).equals(""))
            sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");


        //== 查詢條件 ED ==

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "SOLT001." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
     * JOIN 其它 TABLE 將欄位的值抓出來
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut002Stut003Syst002Syst001FourUse(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) sql.delete(0, sql.length());

            if(!Utility.nullToSpace(ht.get("SQL")).equals("")){
                sql.append(
                    "SELECT H.CENTER_NAME,G.CODE_NAME AS CSMS,A.AYEAR,A.SMS,F.CODE_NAME AS CSTTYPE,D.CRS_NAME, C.NAME, A.STNO, A.CRSNO ,A.MASTER_CLASS_CODE FROM REGT007 A "+
                    "JOIN STUT003 B ON A.STNO = B.STNO JOIN STUT002 C ON "+
                    "C.IDNO = B.IDNO JOIN COUT002 D ON A.CRSNO = D.CRSNO JOIN STUT004 E ON A.AYEAR = E.AYEAR AND A.SMS = E.SMS AND A.STNO = E.STNO JOIN "+
                    "SYST001 F ON F.KIND = 'STTYPE' AND F.CODE = E.STTYPE JOIN SYST001 G ON G.KIND = 'SMS' AND G.CODE = A.SMS "+
                    "JOIN SYST002 H ON H.CENTER_CODE = B.CENTER_CODE "
                );
            }else{
                sql.append(
                    "SELECT F.CRS_NAME,E.CODE_NAME AS CSMS, D.CENTER_NAME, B.CENTER_CODE,C.NAME, A.TAKE_DATE,A.AYEAR, A.SMS, A.STNO, A.CRSNO,A.UNTAKECRS_MK " +
                    "FROM REGT007 A JOIN STUT003 B ON A.STNO = B.STNO JOIN STUT002 C ON " +
                    "B.IDNO = C.IDNO JOIN SYST002 D ON B.CENTER_CODE = D.CENTER_CODE " +
                    "JOIN SYST001 E ON E.KIND = 'SMS' AND E.CODE = A.SMS JOIN COUT002 F ON F.CRSNO = A.CRSNO " +
                    "WHERE 1 = 1 "
                );
                 if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
                    sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
                 if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
                    sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
                 if(!Utility.nullToSpace(ht.get("STNO")).equals(""))
                    sql.append("AND A.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
            }
        //== 查詢條件 ED ==

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "SOLT001." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
//                   System.out.print(rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }




     /**
     * 各科目學生基本資料統計表 - 取得修各科目的學生數 (REGT007)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public Vector getRegt007ForUse_2(Hashtable ht) throws Exception
    {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
            sql.append
            (
                "SELECT LTRIM (a.AYEAR, '0') as AYEAR, b.CODE_NAME as CSMS , a.CRSNO, COUNT(1) AS CRS_COUNT  " +
                "FROM REGT007 a JOIN SYST001 b ON a.SMS=b.CODE AND b.KIND='SMS' "+
                "WHERE a.AYEAR||a.SMS < '" + Utility.nullToSpace(ht.get("AYEAR")) + Utility.nullToSpace(ht.get("SMS")) + "' " +
                "AND a.unqual_take_mk = 'N' " +
                "AND a.untakecrs_mk = 'N' " +
                "AND a.payment_status = '2' " +
                "GROUP BY a.AYEAR, a.SMS, a.CRSNO, b.CODE_NAME "+
                "ORDER BY a.AYEAR, a.SMS, a.CRSNO "
            );

            DBResult    rs      =   null;
            // 依分頁取出資料
            try {
                if(pageQuery) {
                    // 依分頁取出資料
                    rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
                } else {
                    // 取出所有資料
                    rs = dbmanager.getSimpleResultSet(conn);
                    rs.open();
                    rs.executeQuery(sql.toString());
                }
                Hashtable rowHt = null;
                while (rs.next()) {
                    rowHt = new Hashtable();
                    /** 將欄位抄一份過去 */
                    for (int i = 1; i <= rs.getColumnCount(); i++)
                        rowHt.put(rs.getColumnName(i), rs.getString(i));

                    result.add(rowHt);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if(rs != null) {
                    rs.close();
                }
            }
            return result;
    }


    public Vector getRegt007Count_ForUse(Hashtable ht) throws Exception
    {
        if(ht == null) ht = new Hashtable();
        Vector result = new Vector();
        if(sql.length() > 0) sql.delete(0, sql.length());
        String year_sms_str = new String();
        String other_where  = new String();
        String other_where2  = new String();
        String title1 = new String();

        if(!Utility.nullToSpace(ht.get("title1")).equals(""))
             title1 = ht.get("title1").toString();
        if(!Utility.nullToSpace(ht.get("other_where")).equals(""))
             other_where = ht.get("other_where").toString();
        if(!Utility.nullToSpace(ht.get("other_where2")).equals(""))
             other_where2 = ht.get("other_where2").toString();
        if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
             other_where += " AND B.ALLOCATE_CMPS_CODE='" + ht.get("CMPS_CODE").toString() +"' ";
        // STUT003 A   REGT007 B   STUT002 C
        if(Utility.nullToSpace(ht.get("AYEAR")).equals(""))
            year_sms_str=" ";
        else if(!Utility.nullToSpace(ht.get("AYEAR2")).equals(""))
            year_sms_str=" AND B.AYEAR BETWEEN " + ht.get("AYEAR").toString() + " AND " + ht.get("AYEAR2").toString();
        else if(!Utility.nullToSpace(ht.get("累計")).equals(""))
            year_sms_str=" AND B.AYEAR<='" + ht.get("AYEAR").toString() + "' ";
        else
            year_sms_str=" AND B.AYEAR='" + ht.get("AYEAR").toString() + "' ";

        if((!Utility.nullToSpace(ht.get("SMS")).equals(""))&&(Utility.nullToSpace(ht.get("DONT_USE_SMS")).equals("")))
            year_sms_str+=" AND B.SMS='" + ht.get("SMS").toString() + "' ";


            switch (Integer.parseInt(ht.get("SQL").toString())){
                   case 11:
                        sql.append
                        ( "SELECT CODE,CODE_NAME FROM SYST001 WHERE KIND='"+ title1 + "' ORDER BY CODE ");
                        break;
                    case 2:    //117R
                         sql.append
                         (
                            "SELECT B.TAKE_DATE,B.STNO,B.CRSNO,A.CRS_NAME,C.CRD_FEE*A.CRD AS DCRD_FEE,C.MISC_FEE,C.MAG_FEE,D.LAB_FEE "+
                            "FROM REGT001 C "+
                            "JOIN REGT007 B ON C.ASYS=B.ASYS AND C.AYEAR=B.AYEAR AND C.SMS=B.SMS "+
                            "JOIN COUT002 A ON A.CRSNO=B.CRSNO "+
                            "LEFT JOIN REGT002 D ON D.CRSNO=B.CRSNO "
                         );
                         break;
                    case 3:     //102r 115r 119r 130r 137r 107r 125r  get_count
                         if(!Utility.nullToSpace(ht.get("TITLE")).equals("")) sql.append("SELECT "+ht.get("TITLE").toString());
                         else sql.append("SELECT COUNT(*) AS TOTAL");
                         if(!Utility.nullToSpace(ht.get("title1")).equals("")) sql.append(","+ title1);
                         sql.append(" FROM (SELECT A.STNO,C.SEX,A.STTYPE,A.CENTER_CODE,A.REDUCE_TYPE,C.VOCATION,C.EDUBKGRD_GRADE ");    // stut002 stut003
                         if(!Utility.nullToSpace(ht.get("USE_REGT007")).equals("")) sql.append(",B.AYEAR,B.SMS,B.CRSNO ");  // regt007
                             sql.append(",TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(SUBSTR(A.BIRTHDATE,1,4)) AS AGE "+
                                        ",(SUBSTR(A.ENROLL_AYEARSMS,1,3)) AS ENTER_YEARSMS "+
                                        " FROM STUT003 A "+
                                        "JOIN STUT002 C ON A.IDNO=C.IDNO ");
                         if(!Utility.nullToSpace(ht.get("USE_REGT007")).equals("")){   // regt007
                             sql.append(" JOIN REGT005 D ON D.STNO=A.STNO "+
                                        " JOIN REGT007 B ON D.STNO=B.STNO AND D.AYEAR=B.AYEAR AND D.SMS=B.SMS "+

                                        " WHERE (B.UNQUAL_TAKE_MK<>'Y' OR B.UNQUAL_TAKE_MK IS NULL) "+
                                        " AND B.UNTAKECRS_MK<>'Y' AND B.PAYMENT_STATUS NOT IN ('1') "+ year_sms_str+other_where+ ") ");
                         }else{
                             sql.append(" WHERE 0=0 "+ other_where2 +" ) WHERE 0=0 " + other_where);
                         }
                         if(!Utility.nullToSpace(ht.get("title1")).equals("")) sql.append(" GROUP BY "+ title1);
                         break;
                    case 4:  //107r  get_crs_name
                         sql.append("SELECT "+ title1 + " FROM (SELECT B.CRSNO,D.CRS_NAME,D.CRD FROM REGT007 B JOIN COUT002 D ON B.CRSNO=D.CRSNO "+ year_sms_str+other_where+ " )");
                         break;
                    case 5:  //107r  get_reduceREGT003
                         sql.append("SELECT "+ title1 + " FROM (SELECT B.REDUCE_TYPE,B.EXPENSE_TYPE_CODE,B.REDUCE_CRD_FEE_RATE FROM REGT003 B WHERE 0=0 "+ year_sms_str+other_where+ " )");
                         break;
                    case 6:  //107r  get_fee_regt001
                         sql.append("SELECT "+ title1 + " FROM (SELECT B.NETWK_TAKE_SDATE,B.NETWK_TAKE_EDATE,B.VENUE_TAKE_SDATE,B.VENUE_TAKE_EDATE,B.CRD_FEE,B.MISC_FEE FROM REGT001 B WHERE 0=0 "+ year_sms_str+other_where+ " )");
                         break;
                    case 7:    //103r 113r 112r
                         sql.append("SELECT "+ title1 +
                             " FROM ( "+
                             "SELECT A.STNO,B.SEX,SUBSTR(A.ENROLL_AYEARSMS,1,3) AS ENROLL_AYEAR "+
                             "FROM STUT003 A JOIN STUT002 B ON A.IDNO=B.IDNO AND A.BIRTHDATE=C.BIRTHDATE WHERE 0=0 "+year_sms_str+" "+other_where2+") "+
                             "WHERE 0=0 "+ other_where +" ");
                         if(!Utility.nullToSpace(ht.get("title1")).equals("")) sql.append(" GROUP BY "+ title1);
                         break;
                    case 8:	//102r 2008/04/08 by sRu
                    	if(!Utility.nullToSpace(ht.get("TITLE")).equals(""))
                    		sql.append("SELECT "+ht.get("TITLE").toString());
                        else
                        	sql.append("SELECT COUNT(*) AS TOTAL");

                    	if(!Utility.nullToSpace(ht.get("title1")).equals(""))
                    		sql.append(","+ title1);

                        //sql.append(" FROM (SELECT A.STNO,A.STTYPE,A.CENTER_CODE,B.AYEAR,B.SMS ");
                        //sql.append(",TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(SUBSTR(A.BIRTHDATE,1,4)) AS AGE "+
                        //               ",(SUBSTR(A.ENROLL_AYEARSMS,1,3)) AS ENTER_YEARSMS " +
                        //               ",(CASE WHEN substr(A.IDNO, 2, 1) = '1' THEN '1' ELSE '2' END) AS SEX " +
                        //               " FROM STUT003 A "+
                        //               "JOIN STUT002 C ON A.IDNO=C.IDNO ");
                        //sql.append
                        //		(
                        //			" JOIN REGT005 B ON B.STNO=A.STNO " +
                        //			"JOIN REGT007 D ON D.STNO = B.STNO AND D.AYEAR = B.AYEAR AND D.SMS = B.SMS " +
                        //			"WHERE 1 = 1 " +
                        //			"AND B.PAYMENT_STATUS IN ('2', '3', '4') "+
                        //			year_sms_str +
                        //			other_where +
                        //			") "
                        //		);

                    	sql.append
                	 	(
                	 		" FROM( " +
                	 				"SELECT B.STNO,C.SEX,A.CENTER_CODE,B.AYEAR,B.SMS,B.PAYMENT_STATUS,A.ASYS,A.STTYPE, " +
//                	 					"TO_NUMBER(TO_CHAR(SYSDATE,'yyyy'))-TO_NUMBER(SUBSTR(A.BIRTHDATE,1,4)) AS AGE " +
//                	 					",(SUBSTR(A.ENROLL_AYEARSMS,1,3)) AS ENTER_YEARSMS " +
                                        " (CASE " +
                                        "    WHEN ( (  TO_CHAR (SYSDATE, 'MMDD') "       +
                                        "      - SUBSTR (A.BIRTHDATE, 5, 4) ) < 0  )"    +
                                        "    THEN (  TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) "+
                                        "      - SUBSTR (A.BIRTHDATE, 1, 4) - 1  ) "+
                                        "    ELSE (  TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) " +
                                        "       - SUBSTR (A.BIRTHDATE, 1, 4) )             " +
                                        "    END    ) AS AGE  " +
                                        ", (SUBSTR (DECODE (NVL(A.FTSTUD_ENROLL_AYEARSMS,''),'' ,A.ENROLL_AYEARSMS, A.FTSTUD_ENROLL_AYEARSMS), 1, 3)) AS ENTER_YEARSMS "+
                	 				"FROM ( " +
                	 						"SELECT DISTINCT A.AYEAR, A.SMS, A.STNO, A.PAYMENT_STATUS " +
                	 						"FROM REGT005 A, REGT007 B " +
                	 						"WHERE A.STNO = B.STNO " +
                	 						"AND A.AYEAR = B.AYEAR " +
                	 						"AND A.SMS = B.SMS " +
                	 					   ") B " +
                	 				"JOIN STUT003 A ON B.STNO = A.STNO " +
                	 				"JOIN STUT002 C ON A.IDNO = C.IDNO AND  A.BIRTHDATE = C.BIRTHDATE   " +
                	 				"WHERE A.BIRTHDATE IS NOT NULL " +
                	 				"AND B.PAYMENT_STATUS IN ('2', '3', '4') " +
                	 				year_sms_str +
                        	 		other_where +
                	 				") " +
                	 		"WHERE 0 = 0 "
                		);

                        if(!Utility.nullToSpace(ht.get("title1")).equals("")) sql.append(" GROUP BY "+ title1);
                    default :
                         break;
            }

            DBResult    rs      =   null;
            // 依分頁取出資料
            try {
                if(pageQuery) {
                    // 依分頁取出資料
                    rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
                } else {
                    // 取出所有資料
                    rs = dbmanager.getSimpleResultSet(conn);
                    rs.open();
                    rs.executeQuery(sql.toString());
                }
                Hashtable rowHt = null;
                while (rs.next()) {
                    rowHt = new Hashtable();
                    /** 將欄位抄一份過去 */
                    for (int i = 1; i <= rs.getColumnCount(); i++)
                        rowHt.put(rs.getColumnName(i), rs.getString(i));

                    result.add(rowHt);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if(rs != null) {
                    rs.close();
                }
            }

//             System.out.println(sql);
            return result;
    }

	 /**
     * 各科目學生基本資料統計表 - 取得修各科目的學生數 (REGT007)
     * @param ht 條件值
     * @return DBResult
     * @throws Exception
     */
    public Vector getRegt007ForCou105R(Hashtable ht) throws Exception
    {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
            sql.append
            (
                "SELECT LTRIM (a.AYEAR, '0') as AYEAR, b.CODE_NAME as CSMS , a.AYEAR AYEAR2, a.SMS, a.CRSNO, COUNT(1) AS CRS_COUNT  " +
                "FROM REGT007 a JOIN SYST001 b ON a.SMS=b.CODE AND b.KIND='SMS' "+
                "WHERE a.AYEAR||a.SMS < '" + Utility.nullToSpace(ht.get("AYEAR")) + Utility.nullToSpace(ht.get("SMS")) + "' " +
                "AND a.crsno='" + Utility.nullToSpace(ht.get("CRSNO")) + "' "+
				"AND a.unqual_take_mk = 'N' " +
                "AND a.untakecrs_mk = 'N' " +
                "AND a.payment_status = '2' " +
                "GROUP BY a.AYEAR, a.SMS, a.CRSNO, b.CODE_NAME "+
                "ORDER BY a.AYEAR, a.SMS, a.CRSNO "
            );

            DBResult    rs      =   null;
            // 依分頁取出資料
            try {
                if(pageQuery) {
                    // 依分頁取出資料
                    rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
                } else {
                    // 取出所有資料
                    rs = dbmanager.getSimpleResultSet(conn);
                    rs.open();
                    rs.executeQuery(sql.toString());
                }
                Hashtable rowHt = null;
                while (rs.next()) {
                    rowHt = new Hashtable();
                    /** 將欄位抄一份過去 */
                    for (int i = 1; i <= rs.getColumnCount(); i++)
                        rowHt.put(rs.getColumnName(i), rs.getString(i));

                    result.add(rowHt);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if(rs != null) {
                    rs.close();
                }
            }
            return result;
    }

    /**
     * SGU003M 通知符合申請獎學金(成績優秀)
     * SGU004M_申請獎學金資料
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Cout001Stut003Scdt008forUse(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0)
            sql.delete(0, sql.length());
        
        String SPECIAL_STTYPE_MK = UtilityX.checkEmpty(ht.get("SPECIAL_STTYPE_MK")," ");
        
        sql.append(
        	"SELECT S04.STNO, S04.AYEAR, S04.SMS, ST03.ASYS, COUNT (CO01.CRSNO) CNTCRS, SUM (CO01.CRD) AS SUMCRS, SC08.AVG_MARK  "+
        	"FROM SCDT004 S04   "+
        	"	    JOIN COUT001 CO01 ON S04.AYEAR = CO01.AYEAR AND S04.SMS = CO01.SMS AND S04.CRSNO = CO01.CRSNO   "+
        	"	    JOIN SCDT008 SC08 ON S04.AYEAR = SC08.AYEAR AND S04.SMS = SC08.SMS AND S04.STNO = SC08.STNO  "+
        	"	    JOIN STUT003 ST03 ON ST03.STNO=S04.STNO AND (ST03.SPECIAL_STTYPE_TYPE IS NULL OR ST03.SPECIAL_STTYPE_TYPE NOT IN ('"+SPECIAL_STTYPE_MK.replaceAll(",", "','")+"') )"+
        	"WHERE   "+
            //排除享有學分學雜費減免者
//            "       0 = (SELECT COUNT(1) FROM REGT004 R04 WHERE R04.AYEAR = S04.AYEAR AND R04.SMS = S04.SMS AND R04.STNO = S04.STNO AND R04.AUDIT_STATUS IN ('1','3')) "+
            "       0 = (SELECT COUNT(1) FROM REGT004 R04 " + 

            "  WHERE R04.AYEAR = '" + Utility.checkNull(ht.get("AYEAR"),"") + "' "+
        	"  AND R04.SMS = '" + Utility.checkNull(ht.get("SMS"),"") + "'   "+
 
//" R04.AYEAR = S04.AYEAR AND R04.SMS = S04.SMS" +
"  AND R04.STNO = S04.STNO AND R04.AUDIT_STATUS IN ('1','3')) "+


// 判斷成績,上下學期:其中期末考均缺考    暑期:期末考缺考 則不篩選出來

        	"	    AND CASE WHEN S04.SMS='3' and S04.FNLMARK =-1 then 'F'   "+
        	"	         when S04.sms IN('1','2') and (S04.FNLMARK =-1 and S04.MIDMARK =-1) then 'F'  "+
        	"	         else 'T' end ='T' "+
        	"	    AND S04.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
        	"	    AND S04.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "'   "+
        	"	    AND SC08.AVG_MARK>='" + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + "'  "+
        	"	    AND ST03.CENTER_CODE LIKE '" + Utility.checkNull(ht.get("CENTER_CODE"),"") + "%'  "+
        	"       AND 0 < (SELECT COUNT(1) FROM REGT007 RE07 "+
          	"                WHERE RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR"),"") + "' "+
          	"                AND   RE07.SMS = '" + Utility.checkNull(ht.get("SMS"),"") + "' "+
          	"                AND   RE07.STNO = S04.STNO "+
            "                AND   RE07.UNQUAL_TAKE_MK = 'N' "+
            "                AND   RE07.UNTAKECRS_MK = 'N' "+
            "                AND   RE07.PAYMENT_STATUS <> '1') "+
            "      AND  ((ST03.ASYS = '1'  AND "+
            "             0 = (SELECT COUNT(1) FROM GRAT003 G03 "+
            "                  WHERE G03.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
            "                  AND   G03.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
            "                  AND   G03.IDNO = ST03.IDNO "+
            "                  AND   G03.GRAD_REEXAM_STATUS = '2')) OR "+
            "            (ST03.ASYS = '2' AND "+
            "             0 = (SELECT COUNT(1) FROM GRAV014 G14,STUT003 B "+
            "                  WHERE G14.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
            "                  AND   G14.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
            "                  AND   G14.STATUS = '2' "+
            "                  AND   G14.STNO = B.STNO "+
            "                  AND   B.IDNO = ST03.IDNO))) "+
        	"GROUP BY S04.STNO, ST03.ASYS, S04.AYEAR, S04.SMS, SC08.AVG_MARK  "+
        	"HAVING COUNT (CO01.CRSNO) >= '"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"") +"' and SUM (CO01.CRD) >= '"+ Utility.nullToSpace(ht.get("MIN_PT_CRD_CNT"))+"' "
        );

        /*
        sql.append(
                 " SELECT ST03.STNO , RE07.AYEAR ,RE07.SMS ,RE07.ASYS ,COUNT(CO01.CRSNO) CNTCRS, SUM(CO01.CRD) AS SUMCRS  , SC08.AVG_MARK AS AVG_MARK " +
                 "   FROM REGT007 RE07, COUT001 CO01  ,  STUT003 ST03 , SCDT008 SC08  " +
                 "  WHERE RE07.AYEAR = CO01.AYEAR AND  RE07.SMS =CO01.SMS AND RE07.CRSNO = CO01.CRSNO AND RE07.STNO = ST03.STNO " +
                 //"    AND RE07.AYEAR = SC08.AYEAR AND  RE07.SMS = SC08.SMS AND SC08.STNO = ST03.STNO  AND ST03.ENROLL_STATUS= '2'  " +
                 //by poto
                 "    AND RE07.AYEAR = SC08.AYEAR AND  RE07.SMS = SC08.SMS AND SC08.STNO = ST03.STNO  " +
                 //斷別條件為當期
                 "    AND NOT EXISTS ( SELECT STNO FROM REGT004 WHERE AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "' AND STNO = RE07.STNO) " +
                 "    AND NOT EXISTS ( SELECT STNO FROM SGUT003 WHERE ASYS = RE07.ASYS AND AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "' AND STNO = RE07.STNO AND RE_AUDIT_RESULT = '2' ) "

         );
        if(!Utility.checkNull(ht.get("MIN_MARK_AVG"),"").equals("")) {
            sql.append("AND SC08.AVG_MARK >= " + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + " ");
        }
        //判別條件為前期
        if(!Utility.checkNull(ht.get("AYEAR1"),"").equals("")) {
            sql.append("AND RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' ");
        }
        if(!Utility.checkNull(ht.get("SMS1"),"").equals("")) {
            sql.append("AND RE07.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' ");
        }
        if(!Utility.checkNull(ht.get("CENTER_CODE"),"").equals("")) {
            sql.append("AND ST03.CENTER_CODE = '" + Utility.checkNull(ht.get("CENTER_CODE"),"") + "' ");
        }

        sql.append(  "   GROUP BY ST03.STNO , RE07.AYEAR ,RE07.SMS ,RE07.ASYS  , SC08.AVG_MARK  " +
        		     "   HAVING  COUNT(CO01.CRSNO) >='"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"") +"' AND SUM(CO01.CRD) >= '"+ Utility.nullToSpace(ht.get("MIN_PT_CRD_CNT"))+"' " +
                     "   ORDER BY ST03.STNO "

                  );
*/
        System.out.println("SGU003M:"+sql.toString());
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
      * SGU003M 通知符合申請獎學金(身心障礙)
      * SGU004M_申請獎學金資料
      * @param ht 條件值
      * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
      *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
      * @throws Exception
      */
      public Vector getCout001Stut003Sgut004Regt007forUse(Hashtable ht) throws Exception {
         Vector result = new Vector();

         if(sql.length() > 0)
             sql.delete(0, sql.length());
         
         String SPECIAL_STTYPE_MK = UtilityX.checkEmpty(ht.get("SPECIAL_STTYPE_MK")," ");
         
         sql.append(
             	"SELECT S04.STNO, S04.AYEAR, ST03.ASYS, COUNT (CO01.CRSNO) CNTCRS, SUM (CO01.CRD) AS SUMCRS, "+
             	// 取得上一個學年的平均成績(上下學期學期平均)
             	"      (SELECT (A.AVG_MARK+B.AVG_MARK)/2 FROM SCDT008 A, SCDT008 B WHERE A.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND A.SMS='1' AND B.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND B.SMS='2' AND A.STNO=B.STNO AND B.STNO=S04.STNO) AS AVG_MARK  " +
             	"FROM SCDT004 S04   "+
             	"	    JOIN COUT001 CO01 ON S04.AYEAR = CO01.AYEAR AND S04.SMS = CO01.SMS AND S04.CRSNO = CO01.CRSNO   "+
             	"	    JOIN SCDT008 SC08 ON S04.AYEAR = SC08.AYEAR AND S04.SMS = SC08.SMS AND S04.STNO = SC08.STNO  "+
             	"	    JOIN STUT003 ST03 ON ST03.STNO=S04.STNO AND (ST03.STTYPE='1' OR ST03.ASYS='2') AND (  ST03.SPECIAL_STTYPE_TYPE NOT IN ('"+SPECIAL_STTYPE_MK.replaceAll(",", "','")+"') or ST03.SPECIAL_STTYPE_TYPE is null )"+
             	// 身心障礙
             	"	    JOIN SGUT004 SG04 ON SG04.STNO=S04.STNO AND SG04.HAND_NATIVE='2' AND SG04.HANDICAP_EDU_IDENT = '1' AND (SG04.HANDICAP_EDU_IDENT_MK = 'Y' OR SG04.HANDICAP_EDU_IDENT_AYEAR >= '"+Utility.checkNull(ht.get("AYEAR"), "")+"')  "+
             	"WHERE   "+
             	"	    CASE WHEN S04.SMS='3' and S04.FNLMARK =-1 then 'F'   "+
             	"	         when S04.sms IN('1','2') and (S04.FNLMARK =-1 and S04.MIDMARK =-1) then 'F'  "+
             	"	         else 'T' end ='T'   "+
             	"	    AND S04.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
             	"	    AND ST03.CENTER_CODE LIKE '" + Utility.checkNull(ht.get("CENTER_CODE"),"") + "%'  "+
             	"       AND (0 < (SELECT COUNT(1) FROM REGT007 RE07 "+
              	"                 WHERE RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR"),"") + "' "+
              	"                 AND   RE07.SMS = '" + Utility.checkNull(ht.get("SMS"),"") + "' "+
              	"                 AND   RE07.STNO = S04.STNO "+
                "                 AND   RE07.UNQUAL_TAKE_MK = 'N' "+
                "                 AND   RE07.UNTAKECRS_MK = 'N' "+
                "                 AND   RE07.PAYMENT_STATUS <> '1') or "+
                "            0 < (SELECT COUNT(1) FROM GRAT003 G03 "+
                "                  WHERE G03.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
                "                  AND   G03.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
                "                  AND   G03.STNO = S04.STNO "+
                "                  AND   G03.GRAD_REEXAM_STATUS = '2') or "+
                "            0 < (SELECT COUNT(1) FROM GRAV014 G14 "+
                "                  WHERE G14.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
                "                  AND   G14.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
                "                  AND   G14.STATUS = '2' "+
                "                  AND   G14.STNO = S04.STNO) )"                 
             );

         // 同一個身分證字號申領以6(參數PER_RECEIVER_TIMES)次為限 Start...
         if(!Utility.checkNull(ht.get("PER_RECEIVER_TIMES"), "").equals("")) {
             sql.append(
          		 "  AND NOT EXISTS "+
                 "  ( SELECT STUT003.IDNO FROM SGUT003,STUT003 "+
                 "    WHERE  STUT003.IDNO = ST03.IDNO AND SGUT003.STNO = STUT003.STNO AND SGUT003.RE_AUDIT_RESULT = '2' GROUP BY SGUT003.AYEAR , SGUT003.SMS ,SGUT003.ASYS ,STUT003.IDNO HAVING  COUNT(1) >= '"+Utility.checkNull(ht.get("PER_RECEIVER_TIMES"), "")+"' " +
       	         "  ) "
             );
         }

         //用來取得前學年的成績上下學期平均成績皆為80分以上 Start...
         /*
         if(!Utility.checkNull(ht.get("AYEAR2"), "").equals("") && !Utility.checkNull(ht.get("MIN_MARK_AVG"), "").equals("")) {
        	 sql.append(" AND  EXISTS  " +
        			    "    (  SELECT A.STNO   FROM SCDT008 A, SCDT008 B  "+
                        "       WHERE " +
                        "           A.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' " +
                        "       AND A.SMS = '1'  " +
                        "       AND B.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' " +
                        "       AND  B.SMS = '2'  " +
                        "       AND A.AVG_MARK >= "+Utility.checkNull(ht.get("MIN_MARK_AVG"), "")+" " +
                        "       AND  B.AVG_MARK >= "+Utility.checkNull(ht.get("MIN_MARK_AVG"), "")+
                        "       AND A.STNO = B.STNO  " +
                        "       AND B.STNO = ST03.STNO " +
                        "    ) "
             );
         }
         */

         sql.append("GROUP BY S04.STNO, ST03.ASYS, S04.AYEAR ");
         sql.append("HAVING COUNT (CO01.CRSNO) >= '"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"") +"' " +
         				"and SUM (CO01.CRD) >= '"+ Utility.nullToSpace(ht.get("MIN_PT_CRD_CNT"))+"' "+
         				// 申請身障獎助學金的成績下限
         				"and (SELECT (A.AVG_MARK+B.AVG_MARK)/2 FROM SCDT008 A, SCDT008 B WHERE A.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND A.SMS='1' AND B.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND B.SMS='2' AND A.STNO=B.STNO AND B.STNO=S04.STNO) >=  '" + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + "' "
         );

         // 申請身障助學金的成績上限, 如篩選身障生的助學金時,需排除獎學金的同學,即成績未達申請獎學金的標準,但已達到申請助學金標準<===此段仍為HABING 函數
         if(Utility.nullToSpace(ht.get("SHOLAR_TYPE_CODE2")).equals("2"))
        	 sql.append("and (SELECT (A.AVG_MARK+B.AVG_MARK)/2 FROM SCDT008 A, SCDT008 B WHERE A.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND A.SMS='1' AND B.AYEAR='"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND B.SMS='2' AND A.STNO=B.STNO AND B.STNO=S04.STNO) <  '" + Utility.checkNull(ht.get("GRANT_MARK_AVG"),"") + "' ");

         /*
         sql.append(
                  " SELECT ST03.STNO , RE07.AYEAR  ,ST03.ASYS ,COUNT(CO01.CRSNO) CNTCRS, SUM(CO01.CRD) AS SUMCRS , " +
                  " (  SELECT (A.AVG_MARK+A.AVG_MARK)/2  FROM SCDT008 A, SCDT008 B   " +
                  "    WHERE A.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND  A.SMS = '1' " +
                  "      AND B.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND  B.SMS = '2'   AND A.STNO = B.STNO  AND B.STNO = ST03.STNO )  AS AVG_MARK  " +
                  "   FROM REGT007 RE07, COUT001 CO01  ,  STUT003 ST03  , SGUT004 SG04  , SCDT008 SC08 " +
                  "  WHERE RE07.AYEAR = CO01.AYEAR AND  RE07.SMS =CO01.SMS AND RE07.CRSNO = CO01.CRSNO AND RE07.STNO = ST03.STNO AND  RE07.SMS = SC08.SMS AND SC08.STNO = ST03.STNO  "+
                  "   AND  RE07.AYEAR = SC08.AYEAR AND ST03.STNO = SG04.STNO AND SG04.HAND_NATIVE = '2'  AND (ST03.STTYPE = '1' OR ST03.ASYS = '2')     "
          );

         // 同一個身分證字號申領以6(參數PER_RECEIVER_TIMES)次為限 Start...
         if(!Utility.checkNull(ht.get("PER_RECEIVER_TIMES"), "").equals("")) {
             sql.append(
          		 "  AND NOT EXISTS "+
                 "  ( SELECT STNO FROM SGUT003 WHERE ASYS = RE07.ASYS AND AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "'   AND STNO = RE07.STNO AND RE_AUDIT_RESULT = '2' "+
                 "    UNION "+
       	         "    SELECT STNO FROM SGUT003 WHERE ASYS = RE07.ASYS AND STNO = RE07.STNO AND RE_AUDIT_RESULT = '2' GROUP BY AYEAR , SMS ,ASYS ,STNO HAVING  COUNT(STNO) >= '"+Utility.checkNull(ht.get("PER_RECEIVER_TIMES"), "")+"' ) "

             );
         }

         // 用來取得前學年的成績上下學期平均成績皆為80分以上 Start...
         if(!Utility.checkNull(ht.get("AYEAR2"), "").equals("") && !Utility.checkNull(ht.get("MIN_MARK_AVG"), "").equals("")) {
        	 sql.append(" AND  EXISTS  " +
        			    "    (  SELECT A.STNO   FROM SCDT008 A, SCDT008 B  "+
                        " WHERE A.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND  A.SMS = '1'  " +
                        "   AND B.AYEAR = '"+Utility.checkNull(ht.get("AYEAR2"), "")+"' AND  B.SMS = '2'  " +
                        "   AND A.AVG_MARK >= "+Utility.checkNull(ht.get("MIN_MARK_AVG"), "")+" AND  B.AVG_MARK >= "+Utility.checkNull(ht.get("MIN_MARK_AVG"), "")+
                        "   AND A.STNO = B.STNO  AND B.STNO = ST03.STNO ) "
                        );
//        	 sql.append("  AND AVG_MARK >= "+Utility.checkNull(ht.get("MIN_MARK_AVG"), ""));
         }

         if(!Utility.checkNull(ht.get("AYEAR1"), "").equals("")) {
             sql.append("AND RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"), "") + "' ");
         }
//         if(!Utility.checkNull(ht.get("SMS1"), "").equals("")) {
//             sql.append("AND RE07.SMS = '" + Utility.checkNull(ht.get("SMS1"), "") + "' ");
//         }
         if(!Utility.checkNull(ht.get("CENTER_CODE"),"").equals("")) {
             sql.append("AND ST03.CENTER_CODE = '" + Utility.checkNull(ht.get("CENTER_CODE"), "") + "' ");
         }
         sql.append(  "   GROUP BY ST03.STNO , RE07.AYEAR  ,ST03.ASYS   " +
         		      "   HAVING   SUM(CO01.CRD) >= '"+ Utility.checkNull(ht.get("MIN_PT_CRD_CNT"), "")+"' " +
                      "   ORDER BY ST03.STNO "

                   );

*/

         System.out.println("type2-1:"+sql.toString());
         DBResult rs = null;
         try {
             if(pageQuery) {
                 // 依分頁取出資料
                 rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
             } else {
                 // 取出所有資料
                 rs = dbmanager.getSimpleResultSet(conn);
                 rs.open();
                 rs.executeQuery(sql.toString());
             }
             Hashtable rowHt = null;
             while (rs.next()) {
                 rowHt = new Hashtable();
                 /** 將欄位抄一份過去 */
                 for (int i = 1; i <= rs.getColumnCount(); i++){
                     rowHt.put(rs.getColumnName(i), rs.getString(i));

                     }
                 result.add(rowHt);
             }
         } catch (Exception e) {
             throw e;
         } finally {
             if(rs != null) {
                 rs.close();
             }
         }
         return result;
     }
      /**
       * SGU003M 通知符合申請獎學金(原住民)
       * SGU004M_申請獎學金資料
       * @param ht 條件值
       * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
       *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
       * @throws Exception
       */
       public Vector getRegt007Cout001Stut003Sgut004forUse(Hashtable ht) throws Exception {
          Vector result = new Vector();

          if(sql.length() > 0)
              sql.delete(0, sql.length());
          
          String SPECIAL_STTYPE_MK = UtilityX.checkEmpty(ht.get("SPECIAL_STTYPE_MK")," ");
          
          sql.append(
              	"SELECT S04.STNO, S04.AYEAR, S04.SMS, ST03.ASYS, COUNT (CO01.CRSNO) CNTCRS, SUM (CO01.CRD) AS SUMCRS, SC08.AVG_MARK  "+
              	"FROM SCDT004 S04   "+
              	"	    JOIN COUT001 CO01 ON S04.AYEAR = CO01.AYEAR AND S04.SMS = CO01.SMS AND S04.CRSNO = CO01.CRSNO   "+
              	"	    JOIN SCDT008 SC08 ON S04.AYEAR = SC08.AYEAR AND S04.SMS = SC08.SMS AND S04.STNO = SC08.STNO  "+
              	"	    JOIN STUT003 ST03 ON ST03.STNO=S04.STNO AND (ST03.STTYPE = '1' OR ST03.ASYS = '2')  AND (  ST03.SPECIAL_STTYPE_TYPE NOT IN ('"+SPECIAL_STTYPE_MK.replaceAll(",", "','")+"') or ST03.SPECIAL_STTYPE_TYPE is null ) "+
              	// 原住民
              	"	    JOIN SGUT004 SG04 ON SG04.STNO=S04.STNO AND SG04.HAND_NATIVE='1' AND AUDIT_MK = '2' "+
              	"WHERE   "+
              	"	    CASE WHEN S04.SMS='3' and S04.FNLMARK =-1 then 'F'   "+
              	"	         when S04.sms IN('1','2') and (S04.FNLMARK =-1 and S04.MIDMARK =-1) then 'F'  "+
              	"	         else 'T' end ='T'   "+
              	"	    AND S04.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
              	"	    AND S04.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "'   "+
              	"	    AND SC08.AVG_MARK>='" + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + "'  "+
              	"	    AND ST03.CENTER_CODE LIKE '" + Utility.checkNull(ht.get("CENTER_CODE"),"") + "%'  "+
              	"       AND 0 < (SELECT COUNT(1) FROM REGT007 RE07 "+
              	"                WHERE RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR"),"") + "' "+
              	"                AND   RE07.SMS = '" + Utility.checkNull(ht.get("SMS"),"") + "' "+
              	"                AND   RE07.STNO = S04.STNO "+
                "                AND   RE07.UNQUAL_TAKE_MK = 'N' "+
                "                AND   RE07.UNTAKECRS_MK = 'N' "+
                "                AND   RE07.PAYMENT_STATUS <> '1') "+
                "      AND  ((ST03.ASYS = '1'  AND "+
                "             0 = (SELECT COUNT(1) FROM GRAT003 G03 "+
                "                  WHERE G03.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
                "                  AND   G03.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
                "                  AND   G03.IDNO = ST03.IDNO "+
                "                  AND   G03.GRAD_REEXAM_STATUS = '2')) OR "+
                "            (ST03.ASYS = '2' AND "+
                "             0 = (SELECT COUNT(1) FROM GRAV014 G14,STUT003 B "+
                "                  WHERE G14.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' "+
                "                  AND   G14.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' "+
                "                  AND   G14.STATUS = '2' "+
                "                  AND   G14.STNO = B.STNO "+
                "                  AND   B.IDNO = ST03.IDNO))) "+
              	"GROUP BY S04.STNO, ST03.ASYS, S04.AYEAR, S04.SMS, SC08.AVG_MARK  "+
              	"HAVING COUNT (CO01.CRSNO) >= '"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"") +"' and SUM (CO01.CRD) >= '"+ Utility.nullToSpace(ht.get("MIN_PT_CRD_CNT"))+"' "
              );

          /*
          sql.append(
                   "SELECT ST03.STNO , RE07.AYEAR ,RE07.SMS ,ST03.ASYS ,COUNT(CO01.CRSNO) CNTCRS, SUM(CO01.CRD) AS SUMCRS , SC08.AVG_MARK AS AVG_MARK  " +
                   "FROM REGT007 RE07, COUT001 CO01  ,  STUT003 ST03  , SGUT004 SG04 , SCDT008 SC08 " +
                   "WHERE " +
                   "    RE07.AYEAR = CO01.AYEAR " +
                   "AND RE07.SMS = CO01.SMS " +
                   "AND RE07.CRSNO = CO01.CRSNO " +
                   "AND RE07.STNO = ST03.STNO "+
                   "AND RE07.AYEAR = SC08.AYEAR " +
                   "AND  RE07.SMS = SC08.SMS " +
                   "AND SC08.STNO  = ST03.STNO  " +
                   "AND ST03.STNO = SG04.STNO " +
                   "AND SG04.HAND_NATIVE = '1'  " +
                   "AND (ST03.STTYPE = '1' OR ST03.ASYS = '2') "
           );
          if(!Utility.checkNull(ht.get("MIN_MARK_AVG"),"").equals("")) {
              sql.append("AND SC08.AVG_MARK >= " + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + " ");
          }
          if(!Utility.checkNull(ht.get("AYEAR1"), "").equals("")) {
              sql.append("AND RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"), "") + "' ");
          }
          if(!Utility.checkNull(ht.get("SMS1"), "").equals("")) {
              sql.append("AND RE07.SMS = '" + Utility.checkNull(ht.get("SMS1"), "") + "' ");
          }
          if(!Utility.checkNull(ht.get("CENTER_CODE"),"").equals("")) {
              sql.append("AND ST03.CENTER_CODE = '" + Utility.checkNull(ht.get("CENTER_CODE"), "") + "' ");
          }
          sql.append(  "   GROUP BY ST03.STNO , RE07.AYEAR ,RE07.SMS ,ST03.ASYS ,SC08.AVG_MARK " +
          		       "   HAVING   COUNT(RE07.CRSNO) >= '"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"")+"' " +
                       "   ORDER BY ST03.STNO "

                    );
          */

          DBResult rs = null;
          try {
              if(pageQuery) {
                  // 依分頁取出資料
                  rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
              } else {
                  // 取出所有資料
                  rs = dbmanager.getSimpleResultSet(conn);
                  rs.open();
                  rs.executeQuery(sql.toString());
              }
              Hashtable rowHt = null;
              while (rs.next()) {
                  rowHt = new Hashtable();
                  /** 將欄位抄一份過去 */
                  for (int i = 1; i <= rs.getColumnCount(); i++){
                      rowHt.put(rs.getColumnName(i), rs.getString(i));

                      }
                  result.add(rowHt);
              }
          } catch (Exception e) {
              throw e;
          } finally {
              if(rs != null) {
                  rs.close();
              }
          }
          return result;
      }

       /**
        * SGU004M_申請獎學金資料(特殊成就)
        * @param ht 條件值
        * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
        *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
        * @throws Exception
        */
        public Vector getRegt007Cout001Stut003Scdt008Regt004forUse(Hashtable ht) throws Exception {

           Vector result = new Vector();
           if(sql.length() > 0) {
               sql.delete(0, sql.length());
           }
           
           String SPECIAL_STTYPE_MK = UtilityX.checkEmpty(ht.get("SPECIAL_STTYPE_MK")," ");
           System.out.println(ht);
           sql.append(
                    " SELECT ST03.STNO , RE07.AYEAR ,RE07.SMS ,RE07.ASYS ,COUNT(CO01.CRSNO) CNTCRS, SUM(CO01.CRD) AS SUMCRS " +
                    "   FROM REGT007 RE07 " +
                    "	JOIN COUT001 CO01 ON RE07.AYEAR = CO01.AYEAR AND  RE07.SMS =CO01.SMS AND RE07.CRSNO = CO01.CRSNO  " +
                    "	JOIN STUT003 ST03 ON RE07.STNO = ST03.STNO " +
                    "	JOIN SCDT008 SC08 ON RE07.AYEAR = SC08.AYEAR AND  RE07.SMS = SC08.SMS AND SC08.STNO = ST03.STNO " +
                    "  WHERE 1=1 " +
                    "	 AND ( ST03.SPECIAL_STTYPE_TYPE NOT IN ('"+SPECIAL_STTYPE_MK.replaceAll(",", "','")+"') or ST03.SPECIAL_STTYPE_TYPE is null ) "+
                    //斷別條件為當期
                    "    AND NOT EXISTS ( SELECT STNO FROM REGT004 WHERE AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "' AND STNO = RE07.STNO) " +
                    "    AND NOT EXISTS ( SELECT STNO FROM SGUT003 WHERE ASYS = RE07.ASYS AND AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "' AND STNO = RE07.STNO AND RE_AUDIT_RESULT = '2' ) "+
                    "    AND EXISTS ( SELECT 1 FROM REGT007 X WHERE X.UNQUAL_TAKE_MK = 'N' AND X.UNTAKECRS_MK = 'N' AND X.AYEAR ='" + Utility.checkNull(ht.get("AYEAR"),"") + "' AND X.SMS ='" + Utility.checkNull(ht.get("SMS"),"") + "' AND X.STNO = RE07.STNO ) " 
                     
            );
           if(!Utility.checkNull(ht.get("MIN_MARK_AVG"),"").equals("")) {
               sql.append("AND SC08.AVG_MARK >= " + Utility.checkNull(ht.get("MIN_MARK_AVG"),"") + " ");
           }
           //判別條件為前期
           if(!Utility.checkNull(ht.get("AYEAR1"),"").equals("")) {
               sql.append("AND RE07.AYEAR = '" + Utility.checkNull(ht.get("AYEAR1"),"") + "' ");
           }
           if(!Utility.checkNull(ht.get("SMS1"),"").equals("")) {
               sql.append("AND RE07.SMS = '" + Utility.checkNull(ht.get("SMS1"),"") + "' ");
           }
           if(!Utility.checkNull(ht.get("CENTER_CODE"),"").equals("")) {
               sql.append("AND ST03.CENTER_CODE = '" + Utility.checkNull(ht.get("CENTER_CODE"),"") + "' ");
           }
           if(!Utility.checkNull(ht.get("STNO"),"").equals("")) {
               sql.append("AND ST03.STNO = '" + Utility.checkNull(ht.get("STNO"),"") + "' ");
           }

           sql.append(  "   GROUP BY ST03.STNO , RE07.AYEAR ,RE07.SMS ,RE07.ASYS  " +
           		     	"   HAVING  COUNT(CO01.CRSNO) >='"+ Utility.checkNull(ht.get("MIN_PT_CRS_CNT"),"") +"' AND SUM(CO01.CRD) >= '"+ Utility.nullToSpace(ht.get("MIN_PT_CRD_CNT"))+"' " +
                        "   ORDER BY ST03.STNO "

                     );


           DBResult rs = null;
           try {
               if(pageQuery) {
                   // 依分頁取出資料
                   rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
               } else {
                   // 取出所有資料
                   rs = dbmanager.getSimpleResultSet(conn);
                   rs.open();
                   rs.executeQuery(sql.toString());
               }
               Hashtable rowHt = null;
               while (rs.next()) {
                   rowHt = new Hashtable();
                   /** 將欄位抄一份過去 */
                   for (int i = 1; i <= rs.getColumnCount(); i++){
                       rowHt.put(rs.getColumnName(i), rs.getString(i));

                       }
                   result.add(rowHt);
               }
           } catch (Exception e) {
               throw e;
           } finally {
               if(rs != null) {
                   rs.close();
               }
           }
           return result;
       }
        
        /**
         * SGU004M_申請獎學金資料(特殊成就)
         * @param ht 條件值
         * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
         *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
         * @throws Exception
         */
         public Vector getSguSpecialApp(Hashtable ht) throws Exception {

        	 String AYEAR = Utility.checkNull(ht.get("AYEAR"), "");
             String SMS = Utility.checkNull(ht.get("SMS"), "");
             Vector result = new Vector();
             DBResult rs = null;
             try {
	             //---前期資料----------------------------------------------------//
	     		 String SMS1 = "";
	             String AYEAR1 = AYEAR;
	             if(SMS.equals("1")){
	         	    SMS1 = "2";
	         	    AYEAR1 = Utility.fillZero((Integer.parseInt(AYEAR)-1)+"", 3);    	   
	             } else if(SMS.equals("2")){
	         	    SMS1 = "1";
	     		 }
	     		
	     		 Hashtable htResuest = new Hashtable();
	             htResuest.put("AYEAR" , AYEAR);
	             htResuest.put("SMS" , SMS);
	             htResuest.put("STNO" , Utility.checkNull(ht.get("STNO"),""));
	             htResuest.put("AYEAR1" , AYEAR1);
	             htResuest.put("SMS1" , SMS1);
	             //------------------------------------------------------------------//
	     	
	             SGUT001DAO sgut001dao = new  SGUT001DAO(dbmanager, conn);
	             sgut001dao.setResultColumn(" MIN_PT_CRS_CNT , MIN_PT_CRD_CNT , PER_RECEIVER_TIMES , BEHAVASSESS , GRANT_MARK_AVG , MIN_MARK_AVG ");
	             sgut001dao.setSCHOLAR_TYPE_CODE("02");
	             rs = sgut001dao.query();
	             
	             htResuest.put("SHOLAR_TYPE_CODE","02");
	     	
	             if(rs.next())
	             {
	                 htResuest.put("MIN_PT_CRS_CNT",Utility.checkNull(rs.getString("MIN_PT_CRS_CNT"),"0"));
	                 htResuest.put("MIN_PT_CRD_CNT",Utility.checkNull(rs.getString("MIN_PT_CRD_CNT"),"0"));
	                 htResuest.put("MIN_MARK_AVG",Utility.checkNull(rs.getString("MIN_MARK_AVG"),"0"));
	                 htResuest.put("BEHAVASSESS",Utility.checkNull(rs.getString("BEHAVASSESS"),"0"));
	             }
	             
	             REGT007GATEWAY  regt007geteway  =    new REGT007GATEWAY(dbmanager, conn);
	             result =  regt007geteway.getRegt007Cout001Stut003Scdt008Regt004forUse(htResuest);
               
            } catch (Exception e) {
                throw e;
            } finally {
                if(rs != null) {
                    rs.close();
                }
            }
            return result;
        }
        
       /**
     * PLA110R  全校各科選課人數一覽表
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
	public Vector getRegt007Plat002Print1(Hashtable ht) throws Exception {
		DBResult rs = null;
		Vector vt = new Vector();
		try
		{
			String AYEAR = (String)ht.get("AYEAR");
			String SMS = (String)ht.get("SMS");
			String CENTER_ABRCODE = (String)ht.get("CENTER_ABRCODE");
			String CMPS_CODE = (String)ht.get("CMPS_CODE");
			String PAYMENT_STATUS = (String)ht.get("PAYMENT_STATUS");

			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append
			(
				"SELECT e.CENTER_CODE, e.CENTER_NAME, b.CMPS_CODE, b.CMPS_NAME, \n" +
				"	   d.LOC_X||d.LOC_Y AS SN, \n" +
				"	   c.CRSNO, c.CRS_NAME, a.CNT AS TOTAL \n" +
				"FROM ( \n" +
				"	 SELECT a.AYEAR, a.SMS, d.CENTER_ABRCODE, c.CMPS_CODE, a.CRSNO, COUNT(*) AS CNT \n" +
				"	 FROM REGT007 a  \n" +
				"	 JOIN STUT003 b ON a.STNO=b.STNO \n" +
				"	 JOIN SYST002 d ON b.CENTER_CODE=d.CENTER_CODE \n" +
				"	 JOIN PLAT002 c ON a.AYEAR=c.AYEAR AND a.SMS=c.SMS AND A.TUT_CMPS_CODE=c.CMPS_CODE and c.center_abrcode = d.center_abrcode \n" +
				"	 WHERE a.AYEAR='" + AYEAR + "' AND a.SMS='" + SMS + "' AND A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' \n" +
				((!CENTER_ABRCODE.equals(""))?"	 AND c.CENTER_ABRCODE = '" + CENTER_ABRCODE + "' \n":"") +
				((!CMPS_CODE.equals(""))?"	 and a.TUT_CMPS_CODE = '" + CMPS_CODE + "' \n":"") +
				// 不含未繳費
				((PAYMENT_STATUS.equals("Y"))?"	 and a.PAYMENT_STATUS > '1' \n":"") +
				"	 GROUP BY a.AYEAR, a.SMS,d.CENTER_ABRCODE, c.CMPS_CODE, a.CRSNO \n" +
				"	 UNION \n" +
				"	 SELECT a.AYEAR, a.SMS, d.CENTER_ABRCODE, c.CMPS_CODE, a.CRSNO, COUNT(*) AS CNT \n" +
				"	 FROM REGT007 a \n" +
				"	 JOIN STUT003 b ON a.STNO=b.STNO \n" +
				"	 JOIN SYST002 d ON b.CENTER_CODE=d.CENTER_CODE \n" +
				"	 JOIN PLAT002 c ON a.AYEAR=c.AYEAR AND a.SMS=c.SMS AND A.LAB_CMPS_CODE=c.CMPS_CODE and c.center_abrcode = d.center_abrcode \n" +
				"	 WHERE a.AYEAR='" + AYEAR + "' AND a.SMS='" + SMS + "'  AND A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' \n" +
				((!CENTER_ABRCODE.equals(""))?"	 AND c.CENTER_ABRCODE = '" + CENTER_ABRCODE + "' \n":"") +
				((!CMPS_CODE.equals(""))?"	 and a.LAB_CMPS_CODE = '" + CMPS_CODE + "' \n":"") +
				// 不含未繳費
				((PAYMENT_STATUS.equals("Y"))?"	 and a.PAYMENT_STATUS > '1' \n":"") +
				"	 GROUP BY a.AYEAR, a.SMS,d.CENTER_ABRCODE, c.CMPS_CODE, a.CRSNO " +
				") a \n" +
				"JOIN PLAT002 b ON a.AYEAR=b.AYEAR AND a.SMS=b.SMS AND a.CENTER_ABRCODE=b.CENTER_ABRCODE AND a.CMPS_CODE=b.CMPS_CODE \n" +
				"JOIN COUT002 c ON a.CRSNO=c.CRSNO \n" +
				"JOIN REGT013 d ON a.AYEAR=d.AYEAR AND a.SMS=d.SMS AND a.CRSNO=d.CRSNO \n" +
				"JOIN SYST002 e ON a.CENTER_ABRCODE=e.CENTER_ABRCODE \n" +
				"ORDER BY e.CENTER_CODE, b.CMPS_CODE, SN "
			);
			System.out.println("getRegt007Plat002Print1:"+sql.toString());
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				vt.add(rowHt);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
		return vt;
	}
     /**
      * PLA111R  全校各科選課人數一覽表
      * @param ht 條件值
      * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
      *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
      * @throws Exception
      */
      public Vector getRegt007Plat002Print2(Hashtable ht) throws Exception {


      Vector result = new Vector();

         if(sql2.length() > 0) {
             sql2.delete(0, sql.length());
         }
            sql2.append
            (

          // " SELECT  ( F.LOC_X||F.LOC_Y ) AS SN , C.CENTER_ABRCODE||'-'||E.CMPS_CODE||'-'||D.CRSNO AS KEY_NO,COUNT(*) AS COUNT "+
           " SELECT  ( F.LOC_X||F.LOC_Y ) AS SN ,D.CRSNO AS CRSNO, COUNT(*) AS COUNT ,D.CRS_NAME AS CRS_NAME "+
           " FROM REGT007 A "+
           " JOIN STUT003 B ON A.STNO=B.STNO "+
           " JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE "+
           " JOIN COUT002 D ON A.CRSNO=D.CRSNO "+
           " JOIN PLAT002 E ON A.AYEAR=E.AYEAR AND A.SMS=E.SMS "+
           " AND C.CENTER_ABRCODE=E.CENTER_ABRCODE AND A.ALLOCATE_CMPS_CODE=E.CMPS_CODE "+
           " JOIN REGT013 F ON A.AYEAR=F.AYEAR AND A.SMS=F.SMS AND A.CRSNO=F.CRSNO  "+
           " WHERE 1 = 1  "
             );

//            A.AYEAR='096' "+
//           " AND A.SMS='1' "+
//           " AND C.CENTER_ABRCODE='0' "+
//           "AND A.ALLOCATE_CMPS_CODE='1'
            sql2.append(" AND A.UNQUAL_TAKE_MK = 'N' ");
            sql2.append(" AND A.UNTAKECRS_MK = 'N' ");

            // 表示不含未繳費
            if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
            	sql2.append(" AND A.PAYMENT_STATUS > '1' ");

         if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
             sql2.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
         if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
             sql2.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
          if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
             sql2.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
          if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
             sql2.append("AND A.ALLOCATE_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");

            sql2.append(" GROUP BY D.CRSNO ,D.CRS_NAME , ( F.LOC_X||F.LOC_Y )   " );
            sql2.append(" ORDER BY ( F.LOC_X||F.LOC_Y ),D.CRSNO , D.CRS_NAME    " );


         DBResult rs = null;
       //  DBResult rs2= null;
         try {
             if(pageQuery) {
                 // 依分頁取出資料
                 rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
             //    rs2 = Page.getPageResultSet(dbmanager, conn, sql2.toString(), pageNo, pageSize);
             } else {
                 // 取出所有資料
                 rs = dbmanager.getSimpleResultSet(conn);
                 rs.open();
                 rs.executeQuery(sql2.toString());
              }

//              Hashtable rowHt2 = new Hashtable();
               ;
               String keyno = new String();
               String count = new String();
               Hashtable rowHt =   null;

             while (rs.next()) {
            	 rowHt = new Hashtable();
                 /** 將欄位抄一份過去 */
                 for (int i = 1; i <= rs.getColumnCount(); i++){
                     rowHt.put(rs.getColumnName(i), rs.getString(i));
                   //  System.out.println(rs.getColumnName(i)+" : " + rs.getString(i));
                 }

                  result.add(rowHt);
             }


         } catch (Exception e) {
             throw e;
         } finally {
             if(rs != null) {
                 rs.close();
             }
              if(rs != null) {
//                 rs2.close();
         }   }
         return result;
     }
    /**
     * pla107r 各校區人數統計表
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003Syst002Print(Hashtable ht) throws Exception {
		String SQL = "";
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
        " SELECT A.AYEAR,A.SMS,C.CENTER_ABRCODE,C.CENTER_NAME, A.TUT_CMPS_CODE AS CMPS_CODE, D.CMPS_NAME,COUNT(DISTINCT A.STNO) AS TOTAL "+
        " FROM REGT007 A "+
        " JOIN STUT003 B ON A.STNO=B.STNO "+
        " JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE "+
        " JOIN PLAT002 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND C.CENTER_ABRCODE=D.CENTER_ABRCODE  AND A.TUT_CMPS_CODE = D.CMPS_CODE "+
        " WHERE A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' "
         );

        // 表示不含未繳費
		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
			sql.append("AND A.PAYMENT_STATUS > '1' ");

       if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
            sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
            sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
            sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
        }


		sql.append( " GROUP BY A.AYEAR,A.SMS,C.CENTER_ABRCODE,C.CENTER_NAME, A.TUT_CMPS_CODE	,D.CMPS_NAME ");

		SQL = sql.toString() + " UNION " + sql.toString().replaceAll("TUT_","LAB_") + " ORDER BY 1, 2, 3, 5 ";



        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(SQL);
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
                    }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
		 * pla107r 各校區人數統計表
		 *
		 * @param ht
		 *            條件值
		 * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
		 *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
		 * @throws Exception
		 */
	public List getRegt007ShitStno(Hashtable ht) throws Exception {
		List list = new ArrayList();
		if (sql.length() > 0) {
			sql.delete(0, sql.length());
		}

		sql.append("SELECT DISTINCT A.STNO, A.ALLOCATE_CMPS_CODE                                                                                      \n");
		sql.append("FROM REGT007 A                                                                                              \n");
		sql.append("JOIN STUT003 B ON A.STNO=B.STNO                                                                             \n");
		sql.append("JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE                                                               \n");
		sql.append("WHERE A.TUT_CMPS_CODE != A.ALLOCATE_CMPS_CODE AND A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' \n");

		// 表示不含未繳費
		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
			sql.append("AND A.PAYMENT_STATUS > '1' ");

		if (!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
			sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
		}
		if (!Utility.nullToSpace(ht.get("SMS")).equals("")) {
			sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
		}
		if (!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
			sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
		}

		sql.append(" GROUP BY A.STNO, A.ALLOCATE_CMPS_CODE ");

		DBResult rs = null;
		Map map = null;
		try {
			if (pageQuery) {
				// 依分頁取出資料
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				// 取出所有資料
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			while (rs.next()) {
				map = new HashMap();
				map.put("STNO", rs.getString("STNO"));
				map.put("ALLOCATE_CMPS_CODE", rs.getString("ALLOCATE_CMPS_CODE"));
				list.add(map);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return list;
	}

	 /**
		 * pla107r 各校區人數統計表
		 *
		 * @param ht
		 *            條件值
		 * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
		 *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
		 * @throws Exception
		 */
	public Map getRegt007StnoResult(Hashtable ht, String stno, String aCmpsCode, String tempCmpsCode) throws Exception {
		Map map = new HashMap();
		if (sql.length() > 0) {
			sql.delete(0, sql.length());
		}

		String ayear = Utility.nullToSpace(ht.get("AYEAR"));
		String sms = Utility.nullToSpace(ht.get("SMS"));
		String centerAbrcode = Utility.nullToSpace(ht.get("CENTER_ABRCODE"));

		sql.append("SELECT DISTINCT A.STNO, D.NAME, A.ALLOCATE_CMPS_CODE, A.TUT_CMPS_CODE, E.CMPS_NAME AS A_CMPS_NAME, F.CMPS_NAME AS T_CMPS_NAME,                               \n");
		sql.append("(SELECT COUNT(X.TUT_CMPS_CODE) FROM REGT007 X WHERE X.AYEAR = A.AYEAR AND X.SMS = A.SMS AND X.STNO = A.STNO AND X.TUT_CMPS_CODE = '" + tempCmpsCode + "') AS T_TOTAL,           \n");
		sql.append("(SELECT COUNT(X.ALLOCATE_CMPS_CODE) FROM REGT007 X WHERE X.AYEAR = A.AYEAR AND X.SMS = A.SMS AND X.STNO = A.STNO AND X.ALLOCATE_CMPS_CODE = '" + aCmpsCode + "') AS A_TOTAL \n");
		sql.append("FROM REGT007 A                                                                                                                                               \n");
		sql.append("JOIN STUT003 B ON A.STNO=B.STNO                                                                                                                              \n");
		sql.append("JOIN STUT002 D ON B.IDNO = D.IDNO                                                                                                                            \n");
		sql.append("JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE                                                                                                                \n");
		sql.append("JOIN PLAT002 E ON A.AYEAR = E.AYEAR AND A.SMS = E.SMS AND C.CENTER_ABRCODE = E.CENTER_ABRCODE AND A.ALLOCATE_CMPS_CODE = E.CMPS_CODE                         \n");
		sql.append("JOIN PLAT002 F ON A.AYEAR = F.AYEAR AND A.SMS = F.SMS AND C.CENTER_ABRCODE = F.CENTER_ABRCODE AND A.TUT_CMPS_CODE = F.CMPS_CODE                              \n");
		sql.append("WHERE A.AYEAR = '" + ayear + "' AND A.SMS = '" + sms + "' AND C.CENTER_ABRCODE = '" + centerAbrcode + "' AND A.STNO = '" + stno + "' AND A.TUT_CMPS_CODE = '" + tempCmpsCode + "' AND A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' \n");

		// 表示不含未繳費
		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
			sql.append("AND A.PAYMENT_STATUS > '1' ");

		DBResult rs = null;
		try {
			if (pageQuery) {
				// 依分頁取出資料
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				// 取出所有資料
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			while (rs.next()) {
				/** 將欄位抄一份過去 */
				for (int i = 1; i <= rs.getColumnCount(); i++) {
					map.put(rs.getColumnName(i), rs.getString(i));
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return map;
	}

     /**
		 * pla107r 各校區人數統計表
		 *
		 * @param ht
		 *            條件值
		 * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
		 *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
		 * @throws Exception
		 */
      public Vector getRegt007Stut003Syst002PrintFuck(Hashtable ht) throws Exception {
 		String SQL = "";
         Vector result = new Vector();
         if(sql.length() > 0) {
             sql.delete(0, sql.length());
         }

         sql.append(
         " SELECT A.AYEAR,A.SMS,C.CENTER_ABRCODE,C.CENTER_NAME, A.ALLOCATE_CMPS_CODE AS CMPS_CODE, D.CMPS_NAME,COUNT(DISTINCT A.STNO) AS TOTAL "+
         " FROM REGT007 A "+
         " JOIN STUT003 B ON A.STNO=B.STNO "+
         " JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE "+
         " JOIN PLAT002 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND C.CENTER_ABRCODE=D.CENTER_ABRCODE  AND A.ALLOCATE_CMPS_CODE = D.CMPS_CODE "+
         " WHERE A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' "
          );

         // 表示不含未繳費
		if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
			sql.append("AND A.PAYMENT_STATUS > '1' ");
         if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
             sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
         }
         if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
             sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
         }
         if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
             sql.append("AND A.ALLOCATE_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
         }
         if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
             sql.append("AND C.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
         }


 		sql.append( " GROUP BY A.AYEAR,A.SMS,C.CENTER_ABRCODE,C.CENTER_NAME, A.ALLOCATE_CMPS_CODE	,D.CMPS_NAME ");

 		SQL = sql.toString() + " UNION " + sql.toString().replaceAll("ALLOCATE_","LAB_") + " ORDER BY 1, 2, 3, 5 ";



         DBResult rs = null;
         try {
             if(pageQuery) {
                 // 依分頁取出資料
                 rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
             } else {
                 // 取出所有資料
                 rs = dbmanager.getSimpleResultSet(conn);
                 rs.open();
                 rs.executeQuery(SQL);
             }
             Hashtable rowHt = null;
             while (rs.next()) {
                 rowHt = new Hashtable();
                 /** 將欄位抄一份過去 */
                 for (int i = 1; i <= rs.getColumnCount(); i++){
                     rowHt.put(rs.getColumnName(i), rs.getString(i));
                     }
                 result.add(rowHt);
             }
         } catch (Exception e) {
             throw e;
         } finally {
             if(rs != null) {
                 rs.close();
             }
         }
         return result;
     }

    /**
     * PLA_106R 無法編入班級學生清冊
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Plat012Print(Hashtable ht) throws Exception {

         Vector result = new Vector();
         if(sql.length() > 0)
             sql.delete(0, sql.length());

             sql.append(" SELECT unique A.AYEAR,A.SMS, u.center_abrcode, p.cmps_code, A.STNO,A.CRSNO  ,");
             sql.append(" u.center_name, p.cmps_name, a.stno, g.name, q.LOC_X || q.LOC_Y as SN, DECODE (NVL (b.crsno, 'X'), 'X', 'O_', 'X_') || a.crsno || ':' || c.times as content, h.crs_name as ccrsno, DECODE(G.AREACODE_HOME,'','',G.AREACODE_HOME || '-')|| G.TEL_HOME AS TEL_HOME,  ");
             sql.append(" DECODE(G.AREACODE_OFFICE,'','',G.AREACODE_OFFICE || '-')|| G.TEL_OFFICE_EXT AS TEL_OFFICE ,G.MOBILE ");
             sql.append(" FROM REGT007 A ");
             sql.append(" JOIN COUT002 H ON A.CRSNO=H.CRSNO ");
             sql.append(" JOIN STUT003 D ON A.STNO=D.STNO ");
             sql.append(" JOIN STUT002 G ON D.IDNO=G.IDNO AND D.BIRTHDATE=G.BIRTHDATE ");
             sql.append(" JOIN SYST002 F ON D.CENTER_CODE=F.CENTER_CODE  ");
             sql.append(" JOIN syst002 u on f.center_abrcode = u.center_abrcode ");
             sql.append(" JOIN plat002 p on a.ayear = p.ayear and a.sms = p.sms and f.center_abrcode = p.center_abrcode and a.tut_cmps_code = p.cmps_code ");
             sql.append(" JOIN regt013 q on a.ayear = q.ayear and a.sms = q.sms and a.crsno = q.crsno ");
             sql.append(" JOIN PLAT023 C ON A.AYEAR=C.AYEAR AND A.SMS=C.SMS AND A.CRSNO=C.CRSNO AND f.center_abrcode = c.center_abrcode AND a.tut_cmps_code = c.cmps_code ");
             sql.append(" LEFT JOIN  ");
             sql.append(" ( ");
             sql.append(" SELECT a.ayear, a.sms, a.center_abrcode, a.cmps_code, a.stno, a.crsno, a.times ");
             sql.append(" FROM (SELECT a.ayear, a.sms, d.center_abrcode, a.tut_cmps_code AS cmps_code, a.stno, a.crsno, b.times ");
             sql.append(" FROM regt007 a JOIN stut003 c ON a.stno = c.stno ");
             sql.append(" JOIN syst002 d ON d.center_code = c.center_code ");
             sql.append(" JOIN plat023 b ON a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND a.tut_cmps_code = b.cmps_code ");
             sql.append(" WHERE 0 = 0 AND a.ayear = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' AND a.sms = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
             if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
             	sql.append("AND d.center_abrcode = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
             	sql.append("AND a.tut_cmps_code = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
             }

             sql.append(") a ");
             sql.append(" JOIN (SELECT a.ayear, a.sms, d.center_abrcode, a.tut_cmps_code AS cmps_code, a.stno, a.crsno, b.times ");
             sql.append(" FROM regt007 a JOIN stut003 c ON a.stno = c.stno JOIN syst002 d ON d.center_code = c.center_code ");
             sql.append(" JOIN plat023 b ON a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND a.tut_cmps_code = b.cmps_code ");
             sql.append(" WHERE 0 = 0 AND a.ayear = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' AND a.sms = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
             if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
             	sql.append("AND d.center_abrcode = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
             	sql.append("AND a.tut_cmps_code = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
             }
             sql.append(" ) b ");
             sql.append(" ON a.ayear = b.ayear AND a.sms = b.sms AND a.center_abrcode = b.center_abrcode AND a.cmps_code = b.cmps_code ");
             sql.append(" AND a.stno = b.stno AND a.crsno <> b.crsno AND a.times = b.times) B ");
             sql.append(" ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND F.CENTER_ABRCODE=B.CENTER_ABRCODE AND A.TUT_CMPS_CODE=B.CMPS_CODE AND A.STNO=B.STNO AND A.CRSNO=B.CRSNO ");
             sql.append(" WHERE 0=0  ");

             if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
                 sql.append(" AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
                 sql.append(" AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
                 sql.append(" AND F.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
                 sql.append(" AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
             }
             sql.append(" and a.stno in (SELECT distinct a.stno     ");
             sql.append(" FROM regt007 a JOIN stut003 c ON a.stno = c.stno ");
             sql.append(" JOIN syst002 d ON d.center_code = c.center_code ");
             sql.append(" WHERE 0 = 0 ");
             sql.append(" AND a.ayear = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
             sql.append(" AND a.sms = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
             if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
             	sql.append("AND d.center_abrcode = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
             }
             if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
             	sql.append("AND a.tut_cmps_code = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
             }
             sql.append(" and a.tut_class_code = '1')");



             sql.append("  ORDER BY A.AYEAR,A.SMS, u.center_abrcode, p.cmps_code, A.STNO,A.CRSNO  ");


         DBResult rs = null;
         try {
             if(pageQuery) {
                 // 依分頁取出資料
                 rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
             } else {
                 // 取出所有資料
                 rs = dbmanager.getSimpleResultSet(conn);
                 rs.open();
                 System.out.println("getRegt007Plat012Print:"+sql.toString());
                 rs.executeQuery(sql.toString());
             }
             Hashtable rowHt = null;
             while (rs.next()) {
                 rowHt = new Hashtable();
                 /** 將欄位抄一份過去 */
                 for (int i = 1; i <= rs.getColumnCount(); i++)
                     rowHt.put(rs.getColumnName(i), rs.getString(i));

                 result.add(rowHt);
             }
         } catch (Exception e) {
             throw e;
         } finally {
             if(rs != null) {
                 rs.close();
             }
         }
         return result;
     }
    /**
     * REG126R 列印各科目學生基本資料統計表 (REGT007,COUT001,STUT002,STUT003)
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003Stut002Cout001Print(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
            //計算此科目此職業人數
        sql.append(
        " SELECT COUNT(A.STNO) AS CRS_COUNT "+
        " FROM REGT007 A "+
        " LEFT JOIN STUT003 B ON A.STNO=B.STNO "+
        " LEFT JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE "+
        " JOIN SYST002 D ON B.CENTER_CODE=D.CENTER_CODE "+
        " WHERE 0=0 "+
        " AND A.UNQUAL_TAKE_MK = 'N' "+
        " AND A.UNTAKECRS_MK = 'N' "
        //" AND A.PAYMENT_STATUS IN ('2','3','4') "
        );

//AND A.AYEAR='095'
//AND B.CENTER_CODE='01'
//AND A.SMS='1'
//AND A.ALLOCATE_CMPS_CODE='1'
//AND A.CRSNO='100114'
//AND C.VOCATION='02'

             String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");          //學年
             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_ABRCODE      =  Utility.checkNull(ht.get("CENTER_ABRCODE"),"");    //中心編號
             String CMPS_CODE           =  Utility.checkNull(ht.get("CMPS_CODE"),"");      //校區編號
             String CRSNO               =  Utility.checkNull(ht.get("CRSNO"),"");          //科目代號
             String VOCATION            =  Utility.checkNull(ht.get("VOCATION"),"");       //職業別
             String EDUBKGRD_GRADE      =  Utility.checkNull(ht.get("EDUBKGRD_GRADE"),"");
             String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");
             String PRT_MANNER          =  Utility.checkNull(ht.get("PRT_MANNER"),"");


             //條件
             if(ASYS.equals("0") || ASYS.equals(""))
               sql.append(" AND A.ASYS IN (1, 2) ");
             else
               sql.append(" AND A.ASYS IN ("+ASYS+") ");
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(CENTER_ABRCODE))
                sql.append(" AND D.CENTER_ABRCODE = '"+CENTER_ABRCODE+ "'");
             if(!"".equals(CMPS_CODE))
                sql.append(" AND A.ALLOCATE_CMPS_CODE = '"+CMPS_CODE+ "'");
             if(!"".equals(CRSNO))
                sql.append(" AND A.CRSNO = '"+CRSNO+ "'");
             if(!"".equals(VOCATION))
                sql.append(" AND C.VOCATION = '"+VOCATION+ "'");
             if(!"".equals(EDUBKGRD_GRADE))
                sql.append(" AND C.EDUBKGRD_GRADE = '"+EDUBKGRD_GRADE+ "'");
             if(!"".equals(CENTER_CODE))
                sql.append(" AND D.CENTER_CODE = '"+CENTER_CODE+ "'");
             if("2".equals(PRT_MANNER))
                 sql.append(" AND NVL(A.PAYMENT_STATUS,'1') != '1' ");
            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }






        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
       /**
     * REG126R 列印各科目學生基本資料統計表 (REGT007,COUT001,STUT002,STUT003) //取得科目名稱
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Cout001Stut003Cout002Print(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
             //校區下的所有科目名稱
            sql.append(
                " SELECT DISTINCT A.CRSNO,D.CRS_NAME "+
                " FROM REGT007 A " +
                " LEFT JOIN COUT001 B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.CRSNO=B.CRSNO "+
                " LEFT JOIN STUT003 C ON A.STNO=C.STNO  "+
                " LEFT JOIN COUT002 D ON A.CRSNO=D.CRSNO "+
                " LEFT JOIN SYST002 E ON C.CENTER_CODE=E.CENTER_CODE "+
                " WHERE 0=0 "+
                //" AND B.EST_RESULT_MK='Y' AND B.OPEN_YN='Y' "+  //正式時需再COUT001加入判斷是否開班
                " AND A.UNQUAL_TAKE_MK ='N'"+
                " AND A.UNTAKECRS_MK ='N' "
             );

             String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");           //學制
             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_ABRCODE      =  Utility.checkNull(ht.get("CENTER_ABRCODE"),"");    //中心編號
             String CMPS_CODE           =  Utility.checkNull(ht.get("CMPS_CODE"),"");      //校區編號
             String orderBy             =  Utility.checkNull(ht.get("orderBy"),"");        //ORDER by
             String PRT_MANNER          =  Utility.checkNull(ht.get("PRT_MANNER"),"");        //ORDER by


             if(ASYS.equals("0") || ASYS.equals(""))
               sql.append(" AND A.ASYS IN (1, 2) ");
             else
               sql.append(" AND A.ASYS IN ("+ASYS+") ");

             //條件
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(CMPS_CODE))
                sql.append(" AND A.ALLOCATE_CMPS_CODE = '"+CMPS_CODE+ "'");
             if("2".equals(PRT_MANNER))
                 sql.append(" AND A.PAYMENT_STATUS <> '1' AND NVL(A.PAYMENT_STATUS,'1') != '1' ");
             if(!"".equals(CENTER_ABRCODE))
                sql.append(" AND E.CENTER_ABRCODE = '"+CENTER_ABRCODE+ "'");
             else
                sql.append(" AND E.CENTER_ABRCODE <> '0'");

            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


             System.out.println("SQL="+sql.toString());



        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                {
                    if(rs.getColumnName(i).equals("CRSNO"))
                    rowHt.put("CODE", rs.getString(i));
                    if(rs.getColumnName(i).equals("CRS_NAME"))
                    rowHt.put("CODE_NAME", rs.getString(i));
                }
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
      /**
     * REG132_1 列印不符選課規定退件學生資料表
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Stut003Print11(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

        sql.append(
        " SELECT F.CRS_NAME ,A.STNO,C.NAME,A.UNQUAL_TAKE_MK,A.CRSNO,D.CENTER_NAME,C.CRRSADDR_ZIP,C.CRRSADDR,D.ZIP,D.ADDR,D.CENTER_ABBRNAME,A.UNQUAL_TAKE_CAUSE ,E.CODE_NAME AS UNQUAL_TAKE_CAUSE_NAME "+
        " FROM REGT007 A "+
        " JOIN STUT003 B ON A.STNO=B.STNO "+
        " JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE "+
        " JOIN SYST002 D ON B.CENTER_CODE= D.CENTER_CODE "+
        " JOIN SYST001 E ON E.KIND ='UNQUAL_TAKE_CAUSE' AND  A.UNQUAL_TAKE_CAUSE = E.CODE "+
        " JOIN COUT002 F ON A.CRSNO = F.CRSNO "+
        " WHERE (A.UNQUAL_TAKE_MK <> 'N' AND  A.UNQUAL_TAKE_MK IS NOT NULL) "+
        " AND A.PAYMENT_STATUS IN ('2','3','4') "

             );

             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");    //中心編號


             //條件
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(CENTER_CODE))
                sql.append(" AND B.CENTER_CODE = '"+CENTER_CODE+ "'");



            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }






        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
     * REG108R 列印課程加改選名冊
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg108rPrint(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }

             String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");           //學制
             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");    //中心編號
             String TAKE_MANNER         =  Utility.checkNull(ht.get("TAKE_MANNER"),"");    //選課方式
             String SDATE               =  Utility.checkNull(ht.get("SDATE"),"");          //起日
             String EDATE               =  Utility.checkNull(ht.get("EDATE"),"");          //迄日
             String SERVICES_FEE        =  Utility.checkNull(ht.get("SERVICES_FEE"),"");   //是否加印服務費
             String orderBy             =  Utility.checkNull(ht.get("orderBy"),"");

        sql.append(
        "SELECT DISTINCT A.UPD_DATE,A.UPD_TIME, A.STNO,F.NAME,D.CRSNO,D.CRS_NAME,G.CRD, "+
               "(CASE WHEN A.UPD_MK='1' THEN '加' ELSE '' END) AS ADD_CRS, "+
               "(CASE WHEN A.UPD_MK='3' THEN '退' ELSE '' END) AS EDD_CRS "
        );
        if("Y".equals(SERVICES_FEE)) {  // 加印服務費的 SQL 欄位
          sql.append(",NVL(H.SERVICES_FEE,0) AS SERVICES_FEE ");
        }
        sql.append(
          "FROM REGW007 A "+
          "JOIN (SELECT AYEAR,SMS,STNO,CENTER_CODE,TAKE_MANNER "+
                  "FROM REGW005 "+
                 "WHERE 0=0 "
        );
        if(!"".equals(AYEAR))
          sql.append("AND AYEAR='"+AYEAR+"' ");
        if(!"".equals(SMS))
          sql.append("AND SMS='"+SMS+"' ");
        if(!"".equals(CENTER_CODE))
          sql.append("AND CENTER_CODE='"+CENTER_CODE+"' ");
        if(!"".equals(TAKE_MANNER))
          sql.append("AND TAKE_MANNER='"+TAKE_MANNER+"' ");
        sql.append(
               ") B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.STNO=B.STNO "+
          "JOIN (SELECT ASYS,AYEAR,SMS,TAKE_SDATE,TAKE_EDATE,AFTER_TAKE_SDATE,AFTER_TAKE_EDATE "+
                  "FROM REGT001 "+
                 "WHERE 0=0 "
        );
        if(!"".equals(ASYS))
          sql.append("AND ASYS='"+ASYS+ "' ");
        if(!"".equals(AYEAR))
          sql.append("AND AYEAR='"+AYEAR+"' ");
        if(!"".equals(SMS))
          sql.append("AND SMS='"+SMS+"' ");
        sql.append(
               ") C ON A.ASYS=C.ASYS AND A.AYEAR=C.AYEAR AND A.SMS=C.SMS "
        );
        if(ht.get("TAKE").toString().equals("S"))
          sql.append("AND A.UPD_DATE BETWEEN C.TAKE_SDATE AND C.TAKE_EDATE ");
        else if(ht.get("TAKE").toString().equals("A"))
          sql.append("AND A.UPD_DATE BETWEEN C.AFTER_TAKE_SDATE AND C.AFTER_TAKE_EDATE ");
        // 加印服務費的 SQL START ===========================================================
        if("Y".equals(SERVICES_FEE)) {
          sql.append(
     "LEFT JOIN (SELECT STNO,UPD_DATE, "+
                       "NVL (SERVICES_FEE1,'0')+NVL (SERVICES_FEE2,'0') AS SERVICES_FEE "+
                  "FROM REGT010 "+
                 "WHERE 0=0 "
          );
          if(!"".equals(AYEAR))
            sql.append("AND AYEAR='"+AYEAR+"' ");
          if(!"".equals(SMS))
            sql.append("AND SMS='"+SMS+"' ");
          sql.append(
                ") H ON A.STNO=H.STNO "
          );
          if(ht.get("TAKE").toString().equals("S"))
            sql.append("AND H.UPD_DATE BETWEEN C.TAKE_SDATE AND C.TAKE_EDATE ");
          else if(ht.get("TAKE").toString().equals("A"))
            sql.append("AND H.UPD_DATE BETWEEN C.AFTER_TAKE_SDATE AND C.AFTER_TAKE_EDATE ");
        }
        // 加印服務費的 SQL END =============================================================
        sql.append(
          "JOIN (SELECT CRSNO,CRS_NAME "+
                  "FROM COUT002 "+
               ") D ON A.CRSNO=D.CRSNO "+
          "JOIN (SELECT STNO,IDNO,BIRTHDATE "+
                  "FROM STUT003 "+
               ") E ON A.STNO=E.STNO "+
          "JOIN (SELECT NAME,IDNO,BIRTHDATE "+
                  "FROM STUT002 "+
               ") F ON E.IDNO=F.IDNO AND E.BIRTHDATE=F.BIRTHDATE "+
          "JOIN (SELECT AYEAR,SMS,CRSNO,CRD "+
                  "FROM COUT001 "+
                 "WHERE 0=0 "
        );
        if(!"".equals(AYEAR))
          sql.append("AND AYEAR='"+AYEAR+"' ");
        if(!"".equals(SMS))
          sql.append("AND SMS='"+SMS+"' ");
        sql.append(
               ") G ON A.AYEAR=G.AYEAR AND A.SMS=G.SMS AND A.CRSNO=G.CRSNO "+
         "WHERE 1=1 "
        );

        //條件
        if(!"".equals(SDATE) && !"".equals(EDATE))
          sql.append("AND A.UPD_DATE BETWEEN '"+SDATE+ "' AND '"+EDATE+"' ");

        sql.append("AND (A.UPD_MK='1' OR A.UPD_MK='3') ");

            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

         /**
     * REG134R 列印學生選課總表    取的中心下全部選課的學生
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg134rSTNOPrint(Hashtable ht) throws Exception {

    	 Vector result = new Vector();
    	 if(sql.length() > 0) {
		 	sql.delete(0, sql.length());
         }
         //取的中心下全部選課的學生
    	 String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");            //學制
         String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
         String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
         String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");    //中心編號
         String CMPS_CODE           =  Utility.checkNull(ht.get("CMPS_CODE"),"");    //中心編號
         String PRT_MANNER          =  Utility.checkNull(ht.get("PRT_MANNER"),"");        //列印方式初選、已繳費
         if("1".equals(PRT_MANNER)){
         sql.append(        	
	        " SELECT UNIQUE A.STNO,C.NAME,D.CODE_NAME AS STTYPE "+
	        " FROM REGT007 A "+
	        " JOIN STUT003 B ON A.STNO=B.STNO "+
	        " JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE "+
	        " JOIN SYST001 D ON B.STTYPE=D.CODE AND D.KIND='STTYPE' "+
	        " WHERE 1=1 "+
	        " AND A.UNTAKECRS_MK='N' AND A.UNQUAL_TAKE_MK='N' "
         );
         }else if("2".equals(PRT_MANNER)){
        	 sql.append(        	
        	" SELECT UNIQUE A.STNO,C.NAME,D.CODE_NAME AS STTYPE "+
        	" FROM REGT007 A "+
            " JOIN STUT003 B ON A.STNO=B.STNO "+
        	" JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE "+
            " JOIN STUT004 E ON A.AYEAR = E.AYEAR AND A.SMS = E.SMS AND A.STNO=E.STNO "						+
        	" JOIN SYST001 D ON B.STTYPE=D.CODE AND D.KIND='STTYPE' "+
            " WHERE 1=1 "+
            " AND A.UNTAKECRS_MK='N' AND A.UNQUAL_TAKE_MK='N' "
          );
    	 }
         	
         //條件
         if(!"".equals(ASYS))
            sql.append(" AND A.ASYS = '"+ASYS+ "'");
         if(!"".equals(AYEAR))
            sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
         if(!"".equals(SMS))
            sql.append(" AND A.SMS = '"+SMS+ "'");
         if(!"".equals(CENTER_CODE)){
        	 if("1".equals(PRT_MANNER)){
        		 sql.append(" AND B.CENTER_CODE = '" + CENTER_CODE + "'");
        	 }else if("2".equals(PRT_MANNER)){
        		 sql.append(" AND E.CENTER_CODE = '" + CENTER_CODE + "'");
        	 }
         }         
         if(!"".equals(CMPS_CODE))
             sql.append(" AND A.TUT_CMPS_CODE = '"+CMPS_CODE+ "'");
         if("2".equals(PRT_MANNER))
             sql.append(" AND A.PAYMENT_STATUS <> '1' ");
         else
             sql.append(" AND A.PAYMENT_STATUS in ('1','2','3','4') ");

            sql.append(" ORDER BY A.STNO ");

        //排序
        if (!"".equals(orderBy))
        {
            sql.append("ORDER BY " + orderBy);
            orderBy = "";
        }


        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
     /**
     * REG134R 列印學生選課總表    取的個別學生選的科目
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg134rCRSNOPrint(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
          //取的個別學生選的科目


        
        String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
        String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
        String STNO                =  Utility.checkNull(ht.get("STNO"),"");           //學號

        sql.append("SELECT  A.CRSNO,B.CRS_NAME,A.TUT_CMPS_CODE,A.TUT_CLASS_CODE AS MASTER_CLASS_CODE,A.ASS_CLASS_CODE ,TO_CHAR(NVL(B.CRD,'0')) AS CRD,");
        sql.append("'1' AS RMK ");
        sql.append("FROM REGT007 A JOIN COUT002 B ON A.CRSNO=B.CRSNO  ");
        sql.append("JOIN PLAT002 P2 ON P2.AYEAR = A.AYEAR AND P2.SMS = A.SMS AND SUBSTR(A.TUT_CLASS_CODE,1,1) = P2.CENTER_ABRCODE AND A.TUT_CMPS_CODE = P2.CMPS_CODE ");
        sql.append("left join plat012 b2 on a.ayear = b2.ayear AND a.sms = b2.sms AND a.crsno = b2.crsno AND a.tut_class_code = b2.class_code " );
        sql.append("JOIN SYST002 S2 ON SUBSTR(A.TUT_CLASS_CODE,1,1) = S2.CENTER_ABRCODE  ");
        sql.append("WHERE 1=1  ");
		sql.append("AND A.STNO ='"+STNO+"'AND A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.UNTAKECRS_MK='N'  AND A.UNQUAL_TAKE_MK='N'  ");
		/** 已增加上課作業班，不再另增列實習 2016/4/29 Maggie
		sql.append("UNION ");
        sql.append("SELECT  A.CRSNO,B.CRS_NAME||'(實習)',A.TUT_CMPS_CODE,A.LAB_CLASS_CODE AS MASTER_CLASS_CODE,A.ASS_CLASS_CODE,NVL('0','0') AS CRD,S2.CENTER_ABBRNAME||P2.CMPS_NAME AS CMPS_CODE,'2' AS RMK ");
        sql.append("FROM REGT007 A JOIN COUT002 B ON A.CRSNO=B.CRSNO  ");
        sql.append("JOIN COUT001 C ON A.CRSNO=C.CRSNO AND A.AYEAR=C.AYEAR AND A.SMS=C.SMS AND NVL(C.LAB_TIMES,0) > 0 ");
        sql.append("JOIN PLAT002 P2 ON P2.AYEAR = A.AYEAR AND P2.SMS = A.SMS AND SUBSTR(A.LAB_CLASS_CODE,1,1) = P2.CENTER_ABRCODE AND A.LAB_CMPS_CODE = P2.CMPS_CODE ");
        sql.append("JOIN SYST002 S2 ON SUBSTR(A.LAB_CLASS_CODE,1,1) = S2.CENTER_ABRCODE  ");
        sql.append("WHERE 1=1 ");
        sql.append("AND A.STNO ='"+STNO+"'AND A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.UNTAKECRS_MK='N' AND A.UNQUAL_TAKE_MK='N'  ");
        */
        sql.append("order by RMK,CRSNO,TUT_CMPS_CODE ");

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

     /**
      * REG106R_學生選課卡    取的個別學生選的科目
      * @param ht 條件值
      * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
      *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
      * @throws Exception
      */
      public Vector getRegt007Reg106rCRSNOPrint(Hashtable ht) throws Exception {

         Vector result = new Vector();
         if(sql.length() > 0) {
             sql.delete(0, sql.length());
         }
           //取的個別學生選的科目


         
         String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
         String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
         String STNO                =  Utility.checkNull(ht.get("STNO"),"");           //學號

         sql.append("SELECT distinct A.CRSNO,B.CRS_NAME,A.TUT_CMPS_CODE,A.TUT_CLASS_CODE AS MASTER_CLASS_CODE,A.ASS_CLASS_CODE,A.LAB_CLASS_CODE ,TO_CHAR(NVL(B.CRD,'0')) AS CRD,");
         sql.append("P2.CMPS_NAME||'-'||P3.CMPS_NAME||'-'||decode(b2.class_kind || b1.class_kind||b3.class_kind,'117','一般+實習面授',decode(b2.class_kind || b1.class_kind,'11','一般面授','12','網路面授','14','遠距面授','31','委任作業','33','委任作業','88','多次面授','17','實習面授')) AS CMPS_NAME, '1' AS RMK ");
         sql.append(",DECODE(C.TEACHING_TYPE_NAME,'34','【統一面授】','' ) AS TEACHING_TYPE_NAME  ");
         sql.append("FROM REGT007 A JOIN COUT002 B ON A.CRSNO=B.CRSNO  ");
         sql.append("left JOIN PLAT002 P2 ON P2.AYEAR = A.AYEAR AND P2.SMS = A.SMS AND SUBSTR(A.TUT_CLASS_CODE,1,1) = P2.CENTER_ABRCODE AND A.TUT_CMPS_CODE = P2.CMPS_CODE ");
         sql.append("left join plat012 b2 on a.ayear = b2.ayear AND a.sms = b2.sms AND a.crsno = b2.crsno AND SUBSTR(A.TUT_CLASS_CODE,1,1) = B2.CENTER_ABRCODE AND A.TUT_CMPS_CODE = B2.CMPS_CODE AND a.tut_class_code = b2.class_code " );
         sql.append("left join plat012 b1 on a.ayear = b1.ayear AND a.sms = b1.sms AND a.crsno = b1.crsno AND SUBSTR(A.TUT_CLASS_CODE,1,1) = B1.CENTER_ABRCODE AND A.TUT_CMPS_CODE = B1.CMPS_CODE AND a.ass_class_code = b1.class_code " );
         sql.append("left JOIN PLAT002 P3 ON P3.AYEAR = A.AYEAR AND P3.SMS = A.SMS AND SUBSTR(A.LAB_CLASS_CODE,1,1) = P3.CENTER_ABRCODE AND A.LAB_CMPS_CODE = P3.CMPS_CODE ");
         sql.append("left join plat012 b3 on a.ayear = b3.ayear AND a.sms = b3.sms AND a.crsno = b3.crsno AND a.lab_class_code = b3.class_code " );
         sql.append("JOIN SYST002 S2 ON SUBSTR(A.TUT_CLASS_CODE,1,1) = S2.CENTER_ABRCODE  ");
         sql.append("JOIN COUT001 C ON C.AYEAR = A.AYEAR AND C.SMS = A.SMS AND C.CRSNO = A.CRSNO   ");
         sql.append("JOIN SYST001 S1 ON S1.KIND = 'TEACHING_TYPE_NAME' AND S1.CODE = C.TEACHING_TYPE_NAME  ");
         sql.append("WHERE 1=1  ");
 		sql.append("AND A.STNO ='"+STNO+"'AND A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.UNTAKECRS_MK='N'  AND A.UNQUAL_TAKE_MK='N'  ");
 		/** 已增加上課作業班，不再另增列實習 2016/4/29 Maggie
 		sql.append("UNION ");
         sql.append("SELECT  A.CRSNO,B.CRS_NAME||'(實習)',A.TUT_CMPS_CODE,A.LAB_CLASS_CODE AS MASTER_CLASS_CODE,A.ASS_CLASS_CODE,NVL('0','0') AS CRD,S2.CENTER_ABBRNAME||P2.CMPS_NAME AS CMPS_CODE,'2' AS RMK ");
         sql.append("FROM REGT007 A JOIN COUT002 B ON A.CRSNO=B.CRSNO  ");
         sql.append("JOIN COUT001 C ON A.CRSNO=C.CRSNO AND A.AYEAR=C.AYEAR AND A.SMS=C.SMS AND NVL(C.LAB_TIMES,0) > 0 ");
         sql.append("JOIN PLAT002 P2 ON P2.AYEAR = A.AYEAR AND P2.SMS = A.SMS AND SUBSTR(A.LAB_CLASS_CODE,1,1) = P2.CENTER_ABRCODE AND A.LAB_CMPS_CODE = P2.CMPS_CODE ");
         sql.append("JOIN SYST002 S2 ON SUBSTR(A.LAB_CLASS_CODE,1,1) = S2.CENTER_ABRCODE  ");
         sql.append("WHERE 1=1 ");
         sql.append("AND A.STNO ='"+STNO+"'AND A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.UNTAKECRS_MK='N' AND A.UNQUAL_TAKE_MK='N'  ");
         */
         sql.append("order by RMK,CRSNO,TUT_CMPS_CODE ");

         DBResult rs = null;
         try {
             if(pageQuery) {
                 // 依分頁取出資料
                 rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
             } else {
                 // 取出所有資料
                 rs = dbmanager.getSimpleResultSet(conn);
                 rs.open();
                 rs.executeQuery(sql.toString());
             }
             Hashtable rowHt = null;
             while (rs.next()) {
                 rowHt = new Hashtable();
                 /** 將欄位抄一份過去 */

                 for (int i = 1; i <= rs.getColumnCount(); i++)
                     rowHt.put(rs.getColumnName(i), rs.getString(i));

                 result.add(rowHt);

             }
         } catch (Exception e) {
             throw e;
         } finally {
             if(rs != null) {
                 rs.close();
             }
         }
         return result;
     }
     
     
     /**
     * REG106R 列印學生選課卡    取的中心下全部選課的學生
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg106rSTNOPrint(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
          //取的中心下全部選課的學生
        sql.append(
            " SELECT DISTINCT A.STNO,C.NAME,D.CODE_NAME AS STTYPE,C.BIRTHDATE,C.CRRSADDR_ZIP,C.CRRSADDR,B.ASYS AS ASYS "+
            " FROM REGT007 A  "+
            " JOIN STUT003 B ON A.STNO=B.STNO "+
            " JOIN STUT004 E ON A.STNO=E.STNO AND A.AYEAR=E.AYEAR AND A.SMS=E.SMS "+
            " JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE  "+
            " JOIN SYST001 D ON E.STTYPE=D.CODE AND D.KIND='STTYPE' "+
            " WHERE 1=1 "+
            " AND A.UNTAKECRS_MK='N' " +
			// BERYL 2008/11/27 有繳錢才印
            " AND A.PAYMENT_STATUS NOT IN '1' "

             );
             String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");            //學制
             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");    //中心編號
             String STNO                =  Utility.checkNull(ht.get("STNO"),"");            //學號
             String S_CLASS_TYPE        =  Utility.checkNull(ht.get("S_CLASS_TYPE"),"");   //課程註記

             //條件
             if(!"".equals(ASYS))
                sql.append(" AND A.ASYS = '"+ASYS+ "'");
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(CENTER_CODE))
                sql.append(" AND E.CENTER_CODE = '"+CENTER_CODE+ "'");
             if(!"".equals(STNO))
                sql.append(" AND B.STNO = '"+STNO+ "' ");
             if(S_CLASS_TYPE.equals("1")){
             	//sql.append(" AND A.S_CLASS_TYPE IS NULL OR A.S_CLASS_TYPE IS NOT NULL ");
             }else if(S_CLASS_TYPE.equals("2")){
             	//sql.append(" AND A.S_CLASS_TYPE IS NOT NULL ");
             }

                sql.append(" ORDER BY A.STNO ");

            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }






        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }


     /**
     * REG111R 選課地址名條
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg111rPrint(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        //取的中心下全部選課的學生
        sql.append("SELECT DISTINCT C.CRRSADDR_ZIP,C.CRRSADDR,C.NAME,B.STNO ");
        sql.append("FROM REGT007 A ");
        sql.append("JOIN STUT003 B ON A.STNO=B.STNO ");
        sql.append("JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE ");
        sql.append("WHERE 1=1 AND A.PAYMENT_STATUS IN ('2','3','4') ");

         String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");            //學制
         String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
         String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
         String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");    //中心編號
         String CRSNO                =  Utility.checkNull(ht.get("CRSNO"),"");            //學號

         //條件
         if(!"".equals(ASYS))
            sql.append(" AND A.ASYS = '"+ASYS+ "'");
         if(!"".equals(AYEAR))
            sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
         if(!"".equals(SMS))
            sql.append(" AND A.SMS = '"+SMS+ "'");
         if(!"".equals(CENTER_CODE))
            sql.append(" AND B.CENTER_CODE = '"+CENTER_CODE+ "'");
         if(!"".equals(CRSNO))
            sql.append(" AND A.CRSNO = '"+CRSNO+ "'");


            sql.append(" ORDER BY B.STNO ");

        //排序
        if (!"".equals(orderBy))
        {
            sql.append("ORDER BY " + orderBy);
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
    /**
     * REG126R 列印各科目學生基本資料統計表 (REGT007,COUT001,STUT002,STUT003)
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public DBResult getRegt007REG126Print(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
            //計算此科目此職業人數
       sql.append(
        " SELECT B.STTYPE,C.SEX,C.BIRTHDATE "+
        " FROM REGT005 R "+
        " JOIN REGT007 A ON R.STNO=A.STNO AND R.AYEAR=A.AYEAR AND R.SMS=A.SMS "+
        " JOIN STUT003 B ON A.STNO=B.STNO "+
        " JOIN STUT002 C ON B.IDNO=C.IDNO AND B.BIRTHDATE=C.BIRTHDATE "+
        " JOIN SYST002 D ON B.CENTER_CODE=D.CENTER_CODE "+
        " WHERE 0=0 "+
        " AND R.TAKE_ABNDN ='N'  "+
        " AND A.UNQUAL_TAKE_MK ='N'  "+
        " AND A.UNTAKECRS_MK ='N' "
        );


             String ASYS                =  Utility.checkNull(ht.get("ASYS"),"");          //學制
             String AYEAR               =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                 =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String CENTER_ABRCODE      =  Utility.checkNull(ht.get("CENTER_ABRCODE"),"");    //中心編號
             String CMPS_CODE           =  Utility.checkNull(ht.get("CMPS_CODE"),"");      //校區編號
             String CRSNO               =  Utility.checkNull(ht.get("CRSNO"),"");          //科目代號
             String VOCATION            =  Utility.checkNull(ht.get("VOCATION"),"");       //職業別
             String EDUBKGRD_GRADE      =  Utility.checkNull(ht.get("EDUBKGRD_GRADE"),"");
             String CENTER_CODE         =  Utility.checkNull(ht.get("CENTER_CODE"),"");
             String PRT_MANNER         =  Utility.checkNull(ht.get("PRT_MANNER"),"");


             //條件
             if(ASYS.equals("0") || ASYS.equals(""))
               sql.append(" AND A.ASYS IN (1, 2) ");
             else
               sql.append(" AND A.ASYS IN ("+ASYS+") ");
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(CENTER_ABRCODE))
                sql.append(" AND D.CENTER_ABRCODE = '"+CENTER_ABRCODE+ "'");
             if(!"".equals(CMPS_CODE))
                sql.append(" AND A.ALLOCATE_CMPS_CODE = '"+CMPS_CODE+ "'");
             if(!"".equals(CRSNO))
                sql.append(" AND A.CRSNO = '"+CRSNO+ "'");
             if(!"".equals(VOCATION))
                sql.append(" AND C.VOCATION = '"+VOCATION+ "'");
             if(!"".equals(EDUBKGRD_GRADE))
                sql.append(" AND C.EDUBKGRD_GRADE = '"+EDUBKGRD_GRADE+ "'");
             if(!"".equals(CENTER_CODE))
                sql.append(" AND D.CENTER_CODE = '"+CENTER_CODE+ "'");
              if("2".equals(PRT_MANNER))
                 sql.append(" AND NVL(R.PAYMENT_STATUS,'1') != '1' ");

            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }






        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


        } catch (Exception e) {
            throw e;
        } finally {
//            if(rs != null) {
//                rs.close();
//            }
        }
        return rs;
    }


     /**
     * REG128R 全修生各學年註冊人數統計表
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg128rPrint(Hashtable ht) throws Exception {

        int ayear1 = Integer.parseInt(ht.get("AYEAR1").toString());
        int ayear2 = Integer.parseInt(ht.get("AYEAR2").toString());
        if(ayear1 > ayear2) {
          int temp = ayear2;
            ayear2 = ayear1;
            ayear1 = temp;
        }

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        /*
        sql.append(
        "SELECT AYEAR,SMS,STTYPE,NEW_OLD,COUNT(STNO) AS ST_COUNT "+
        "FROM( "+
             "SELECT A.AYEAR,A.SMS,B.STTYPE,A.STNO, "+
                    "(CASE WHEN TO_NUMBER(A.AYEAR) = SUBSTR(A.STNO, 0, 2) THEN '1' ELSE '2' END) NEW_OLD "+
               "FROM REGT007 A JOIN STUT003 B ON A.STNO = B.STNO "+
              "WHERE 1 = 1 "+
                "AND (A.UNQUAL_TAKE_MK <> 'Y' OR A.UNQUAL_TAKE_MK IS NULL) "+
                "AND A.UNTAKECRS_MK <> 'Y' "+
                "AND A.PAYMENT_STATUS IN ('2', '3', '4') "+
                "AND A.AYEAR BETWEEN '0"+ayear1+"' AND '0"+ayear2+"' "+
                "AND A.SMS IN ('1','2','3') "+
                "AND B.STTYPE IN('1','2','3') "+
        ") "+
        "GROUP BY AYEAR,SMS,STTYPE,NEW_OLD "+
        "ORDER BY AYEAR,SMS,STTYPE,NEW_OLD "
        );
        */
        sql.append(
        "SELECT AYEAR,SMS,STTYPE,NEW_OLD,COUNT(STNO) AS ST_COUNT "+
        "FROM( "+
             "SELECT A.AYEAR,A.SMS,B.STTYPE,A.STNO, "+
                    "(CASE WHEN  (ENROLL_AYEARSMS = A.AYEAR||A.SMS OR FTSTUD_ENROLL_AYEARSMS = A.AYEAR||A.SMS) THEN '1' ELSE '2' END) NEW_OLD "+
               "FROM REGT005 A JOIN STUT003 B ON A.STNO = B.STNO "+
              "WHERE 1 = 1 "+
                "AND NVL(A.PAYMENT_STATUS,'1') != '1' "+
                "AND A.TAKE_ABNDN = 'N' "+
                "AND A.AYEAR BETWEEN '"+Utility.fillZero(String.valueOf(ayear1), 3) +"' AND '"+Utility.fillZero(String.valueOf(ayear2), 3) +"' "+
                "AND A.SMS IN ('1','2','3') "+
                "AND B.STTYPE IN('1','2','3') "+
        ") "+
        "GROUP BY AYEAR,SMS,STTYPE,NEW_OLD "+
        "ORDER BY AYEAR,SMS,STTYPE,NEW_OLD "
        );
        
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

    public void getREG139R_REG144Rdata(Vector vt, Hashtable ht) throws Exception {
		DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append
			(
				"SELECT A.AYEAR, A.SMS, A.STNO, B.STTYPE, B.CENTER_CODE, "+
				       "SUBSTR(B.IDNO,2,1) AS SEX, "+
				       "C.BIRTHDATE, C.VOCATION, C.EDUBKGRD_GRADE " +
				"FROM REGT007 A, STUT003 B, STUT002 C " +
				"WHERE A.STNO=B.STNO AND B.IDNO=C.IDNO(+) AND B.BIRTHDATE=C.BIRTHDATE AND A.UNTAKECRS_MK='N' "
			);

			sql.append("AND A.AYEAR = '" + Utility.dbStr(ht.get("AYEAR")) + "' ");

			sql.append("AND A.SMS = '" + Utility.dbStr(ht.get("SMS")) + "' ");

			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				vt.add(rowHt);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
	}

	public void getReg145r_Reg152rData(Vector vt, Hashtable ht) throws Exception {
		DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append
			(
				"SELECT a.AYEAR, a.CRSNO, a.SMS, a.STNO, b.STTYPE, a.TUT_CMPS_CODE, LAB_CMPS_CODE, b.CENTER_CODE, c.BIRTHDATE, c.SEX, c.VOCATION, c.EDUBKGRD_GRADE " +
				"FROM REGT007 a, STUT003 b, STUT002 c " +
				"WHERE a.STNO=b.STNO AND b.IDNO=c.IDNO(+) AND a.UNTAKECRS_MK='N' "
			);

			sql.append("AND a.AYEAR = '" + Utility.dbStr(ht.get("AYEAR")) + "' ");

			sql.append("AND a.SMS = '" + Utility.dbStr(ht.get("SMS")) + "' ");

			if(!Utility.checkNull(ht.get("CRSNO"),"").equals("")) {
	             sql.append("AND a.CRSNO = '" + Utility.checkNull(ht.get("CRSNO"), "") + "' ");
	         }

			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				vt.add(rowHt);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
	}

	public void getReg153r_Reg154rData(Vector vt, Hashtable ht) throws Exception {
		DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append
			(
				"SELECT a.AYEAR, a.CRSNO, d.FACULTY_CODE, a.SMS, a.STNO, b.STTYPE, a.TUT_CMPS_CODE, LAB_CMPS_CODE, b.CENTER_CODE, c.BIRTHDATE, c.SEX, c.VOCATION, c.EDUBKGRD_GRADE " +
				"FROM REGT007 a, STUT003 b, STUT002 c, COUT001 d " +
				"WHERE a.STNO=b.STNO AND b.IDNO=c.IDNO(+) AND a.UNTAKECRS_MK='N' AND a.AYEAR=d.AYEAR AND a.SMS=d.SMS AND a.CRSNO=d.CRSNO "
			);

			String s = Utility.dbStr(ht.get("SAYEAR"))+Utility.dbStr(ht.get("SSMS"));
			String e = Utility.dbStr(ht.get("EAYEAR"))+Utility.dbStr(ht.get("ESMS"));

			sql.append("AND a.AYEAR||a.SMS >='"+s+"' AND a.AYEAR||a.SMS <= '"+e+"' ");

			sql.append("ORDER BY a.AYEAR ASC, a.SMS ASC, d.FACULTY_CODE" );

			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				vt.add(rowHt);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
	}
	/**
     * REG135R_繳費證明書
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg135rPrint(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
                 //取出該名學生的學分學雜費,並且排除退費及未開班的科目
        /*
        sql.append(
        " SELECT A.ASYS,E.NAME,SUM(F.CRD) AS TOTAL_CRD_CNT,NVL(SUM(C.CRD_FEE),'0') AS CRD_FEE,NVL(SUM(C.MISC_FEE),'0') AS MISC_FEE "+
        " FROM REGT007 A "+
        " JOIN REGT010 B ON A.ASYS=B.ASYS AND A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.STNO=B.STNO AND A.PAYMENT_STATUS IN('2','3','4') AND (A.UNQUAL_TAKE_MK<>'Y' OR A.UNQUAL_TAKE_MK IS NULL) AND A.UNTAKECRS_MK<>'Y' "+
        " JOIN REGT011 C ON B.AYEAR=C.AYEAR AND B.SMS=C.SMS AND B.WRITEOFF_NO=C.WRITEOFF_NO AND A.CRSNO=C.CRSNO AND B.PAYMENT_STATUS IN('2','3','4') "+
        " JOIN STUT003 D ON A.STNO=D.STNO "+
        " JOIN STUT002 E ON D.IDNO=E.IDNO AND D.BIRTHDATE=E.BIRTHDATE "+
        " JOIN COUT002 F ON A.CRSNO =F.CRSNO "+

        " WHERE 1 = 1 "


             );
        */
        sql.append("SELECT ");
        sql.append("A.ASYS,E.NAME , ");
        sql.append("NVL(B.CRD_FEE,'0') AS CRD_FEE, ");
        sql.append("NVL(B.MISC_FEE,'0') AS MISC_FEE, ");
        sql.append("SUM(F.crd) AS TOTAL_CRD_CNT  ");
        sql.append("FROM REGT007 A  ");
        sql.append("JOIN REGT010 B ON A.ASYS=B.ASYS AND A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.STNO=B.STNO AND A.PAYMENT_STATUS IN('2','3','4') ");
        sql.append("AND (A.UNQUAL_TAKE_MK<>'Y' OR A.UNQUAL_TAKE_MK IS NULL) ");
        sql.append("AND A.UNTAKECRS_MK<>'Y' ");
        sql.append("JOIN STUT003 D ON A.STNO=D.STNO  ");
        sql.append("JOIN STUT002 E ON D.IDNO=E.IDNO AND D.BIRTHDATE=E.BIRTHDATE  ");
        sql.append("JOIN COUT002 F ON A.CRSNO =F.CRSNO  ");
        sql.append("WHERE 1 = 1  ");



          //學制
             String AYEAR                 =  Utility.checkNull(ht.get("AYEAR"),"");          //學年
             String SMS                   =  Utility.checkNull(ht.get("SMS"),"");            //學期
             String STNO                  =  Utility.checkNull(ht.get("STNO"),"");           //學號

             //條件
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(STNO))
                sql.append(" AND A.STNO = '"+STNO+ "'");

             sql.append("group by A.ASYS,E.NAME,B.CRD_FEE,B.MISC_FEE,NVL(SUBSTR(B.WRITEOFF_NO,7),0) ");
             sql.append("ORDER BY NVL(SUBSTR(B.WRITEOFF_NO,7),0) DESC      ");


        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }



     /**
     * REG135R_繳費證明書
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public Vector getRegt007Reg135rPrint1(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
             //取出減免比例
        sql.append(
        " SELECT B.REDUCE_CRD_FEE_RATE AS RATE "+
        " FROM REGT004 A "+
        " JOIN REGT003 B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.REDUCE_TYPE=B.REDUCE_TYPE "+
        " WHERE 1 = 1 "



             );
          //學制
             String AYEAR                 =  Utility.checkNull(ht.get("AYEAR"),"");                //學年
             String SMS                   =  Utility.checkNull(ht.get("SMS"),"");                  //學期
             String STNO                  =  Utility.checkNull(ht.get("STNO"),"");                 //學號
             String EXPENSE_TYPE_CODE     =  Utility.checkNull(ht.get("EXPENSE_TYPE_CODE"),"");    //減免費用類別
             //條件
             if(!"".equals(AYEAR))
                sql.append(" AND A.AYEAR = '"+AYEAR+ "'");
             if(!"".equals(SMS))
                sql.append(" AND A.SMS = '"+SMS+ "'");
             if(!"".equals(STNO))
                sql.append(" AND A.STNO = '"+STNO+ "'");
             if(!"".equals(EXPENSE_TYPE_CODE))
                sql.append(" AND B.EXPENSE_TYPE_CODE = '"+EXPENSE_TYPE_CODE+ "'");




        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */

                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
    /**
     * REG126R 列印各科目學生基本資料統計表 (REGT007,COUT001,STUT002,STUT003)
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     * @throws Exception
     */
     public DBResult getRegt007REG103Print(Hashtable ht) throws Exception {

        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
            //計算此科目此職業人數
        sql.append(
            " SELECT B.ASYS,A.SEX,A.EDUBKGRD_GRADE "+
            " FROM STUT002 A "+
            " JOIN STUT003 B ON A.IDNO=B.IDNO AND A.BIRTHDATE=B.BIRTHDATE "+
            " WHERE 1 = 1 "




             );

             String STNO  =  Utility.checkNull(ht.get("STNO"),"");          //學年


             //條件
             if(!"".equals(STNO))
                sql.append(" AND B.STNO LIKE '"+STNO+"%'");



            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }






        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


        } catch (Exception e) {
            throw e;
        } finally {
//            if(rs != null) {
//                rs.close();
//            }
        }
        return rs;
    }

	public void getAutIndexSResult1(Vector vt, Hashtable ht) throws Exception {
		DBResult rs = null;
		try
		{
			// 取得是否顯示欄位的註記
			PLAT028DAO plat028 = new PLAT028DAO(dbmanager, conn);
			plat028.setResultColumn("CLASS_YN,CLASSRM_YN,EXAM_CLASSRM_YN,TCH_IDNO_YN");
			plat028.setAYEAR(Utility.dbStr(ht.get("AYEAR")));
			plat028.setSMS(Utility.dbStr(ht.get("SMS")));
			rs = plat028.query();

			String classYn = "N";
			String classrmYn = "N";
			String examClassrmYn = "N";
			String tchIdnoYn = "N";

			if(rs.next()){
				classYn = rs.getString("CLASS_YN");
				classrmYn = rs.getString("CLASSRM_YN");
				examClassrmYn = rs.getString("EXAM_CLASSRM_YN");
				tchIdnoYn = rs.getString("TCH_IDNO_YN");
			}
			rs.close();

			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append
			(
				"SELECT DISTINCT a.stno, a.crsno, d.crs_name, b.center_abrcode, c.CENTER_ABBRNAME as center_name,DECODE(P28.CLASS_YN,'Y',a.tut_class_code,'') as class_code,DECODE(P28.CLASS_YN,'Y',a.ass_class_code,'') as ass_class_code, DECODE(P28.CLASS_YN,'Y',DECODE(b5.CLASS_KIND,'7',a.lab_class_code,''),'') as lab_class_code, b.cmps_code, " +
				"b.cmps_code, e.cmps_name||'-'||e1.cmps_name as cmps_name, b.class_kind, g.code_name, b.clssrm_code, decode(b.CLASS_KIND ,'7','0',f.crd) as crd, DECODE(P28.TCH_IDNO_YN,'Y',h.NAME,'') AS NAME, SUBSTR(i.faculty_code,1,1) AS FACULTY_CODE, i.CRS_GROUP_CODE, f.EXAM_MK, f.EXAM_MK_MID, " +
			//	"decode(b.class_kind||b1.class_kind,'11','一般面授','12','網路面授','14','遠距面授','31','委任作業','33','委任作業','88','多次面授','17','實習面授',g.code_name) as class_name "+	
				"decode(b.class_kind || b1.class_kind||b2.class_kind,'117','一般+實習面授',decode(b.class_kind || b1.class_kind,'11','一般面授','12','網路面授','14','遠距面授','31','委任作業','33','委任作業','88','多次面授','17','實習面授')) as class_name " +
				"FROM regt007 a "+ 
				"left join plat028 P28 ON P28.AYEAR = A.AYEAR AND P28.SMS = A.SMS "+
			//	"left join plat012 b on a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND a.tut_cmps_code=b.cmps_code AND a.ASS_class_code = b.class_code " +
				"left join plat012 b on a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND substr(a.tut_class_code,1,1) = b.center_abrcode AND a.tut_cmps_code = b.cmps_code AND a.tut_class_code = b.class_code " +
				"left join plat012 b1 on a.ayear = b1.ayear AND a.sms = b1.sms AND a.crsno = b1.crsno AND a.ass_class_code = b1.class_code " +	
				"left join plat012 b2 on a.ayear = b2.ayear AND a.sms = b2.sms AND a.crsno = b2.crsno AND a.lab_class_code = b2.class_code AND b2.tch_idno is not null " +
				"left join plat012 b5 on a.ayear = b5.ayear AND a.sms = b5.sms AND a.crsno = b5.crsno AND a.lab_class_code = b5.class_code " +
				"left join syst002 c on b.center_abrcode = c.center_abrcode "+
				"join cout002 d on a.crsno = d.crsno "+
				"left join plat002 e on a.ayear = e.ayear AND a.sms = e.sms AND b.center_abrcode = e.center_abrcode AND b.cmps_code = e.cmps_code "+
				"left join plat002 e1 on a.ayear = e1.ayear AND a.sms = e1.sms AND b2.center_abrcode = e1.center_abrcode AND b2.cmps_code = e1.cmps_code " +
				"join cout001 f on a.ayear = f.ayear AND a.sms = f.sms AND a.crsno = f.crsno "+
				"left join syst001 g on b.class_kind = g.code AND g.kind = 'CLASS_KIND' "+
				"left join plat012 b4 "+
				"on a.ayear = b4.ayear AND a.sms = b4.sms AND a.crsno = b4.crsno AND a.ass_class_code = b4.class_code and b4.tch_idno is not null "+
				"left join trat001 h on b4.tch_idno = h.idno "+
				"left join cout103 i on i.ayear=a.ayear and i.sms=a.sms and i.crsno=a.crsno and i.TOTAL_CRS_NO='01' "+
				"WHERE " +
				"    a.AYEAR = '" + Utility.dbStr(ht.get("AYEAR")) + "' "+
				"AND a.SMS = '" + Utility.dbStr(ht.get("SMS")) + "' "+
				"AND a.STNO = '" + Utility.dbStr(ht.get("STNO")) + "' "+
				"AND a.UNQUAL_TAKE_MK = 'N' "+
				"AND a.UNTAKECRS_MK = 'N' "+
				"AND a.PAYMENT_STATUS <> '1' "+
				/** 已增加上課作業班，不再另增列實習 2016/4/29 Maggie
				"UNION "+
				"SELECT DISTINCT a.stno, a.crsno, d.crs_name, b.center_abrcode, c.CENTER_ABBRNAME as center_name,  DECODE(P28.CLASS_YN,'Y',b.class_code,'') as class_code,DECODE(P28.CLASS_YN,'Y',a.ass_class_code,'') as ass_class_code,b.cmps_code, " +
					"e.cmps_name, b.class_kind, g.code_name, b.clssrm_code, decode(b.CLASS_KIND ,'7','0',f.crd) as crd, DECODE(P28.TCH_IDNO_YN,'Y',h.NAME,'') AS NAME, SUBSTR(i.faculty_code,1,1) AS FACULTY_CODE, i.CRS_GROUP_CODE,to_char('') as class_name " +
				"FROM regt007 a "+
				"left join plat028 P28 ON P28.AYEAR = A.AYEAR AND P28.SMS = A.SMS "+
				"left join plat012 b on a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND a.lab_cmps_code=b.cmps_code AND a.lab_class_code = b.class_code " +
				"left join syst002 c on b.center_abrcode = c.center_abrcode "+
				"join cout002 d on a.crsno = d.crsno "+
				"left join plat002 e on a.ayear = e.ayear AND a.sms = e.sms AND b.center_abrcode = e.center_abrcode AND b.cmps_code = e.cmps_code "+
				"join cout001 f on a.ayear = f.ayear AND a.sms = f.sms AND a.crsno = f.crsno "+
				"left join syst001 g on b.class_kind = g.code AND g.kind = 'CLASS_KIND' "+
				"left join trat001 h on b.tch_idno = h.idno "+
				"left join cout103 i on i.ayear=a.ayear and i.sms=a.sms and i.crsno=a.crsno and i.TOTAL_CRS_NO='01' "+
				"WHERE " +
				"    a.AYEAR = '" + Utility.dbStr(ht.get("AYEAR")) + "' "+
				"AND a.SMS = '" + Utility.dbStr(ht.get("SMS")) + "' "+
				"AND a.STNO = '" + Utility.dbStr(ht.get("STNO")) + "' "+
				"AND a.UNQUAL_TAKE_MK = 'N' "+
				"AND a.UNTAKECRS_MK = 'N' "+
				"AND a.PAYMENT_STATUS <> '1' "+
				"AND a.LAB_CMPS_CODE IS NOT NULL "+
				"AND a.S_CLASS_TYPE IS NULL "+
				*/
				"ORDER BY CRSNO, CLASS_CODE, CRS_GROUP_CODE "
			);

			
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Vector tmp = new Vector();
			while (rs.next())
			{
				Hashtable rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));

				// 每一筆(科) 均放入是否開放顯示的註記
				rowHt.put("CLASS_YN", classYn);
				rowHt.put("CLASSRM_YN", classrmYn);
				rowHt.put("EXAM_CLASSRM_YN", examClassrmYn);
				rowHt.put("TCH_IDNO_YN", tchIdnoYn);

				tmp.add(rowHt);
			}
			
			Vector pk = new Vector();
			pk.add("CRSNO");
			pk.add("CLASS_KIND");

			// 將相同科目的採計學系組起來,ex:crsno:100114 --->採計學系10,20,30..
			UtilityX.combinCompareNextTheSameData(pk, "FACULTY_CODE", "", tmp, vt);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
	}

	public DBResult getDataForCou002mHistory(Hashtable ht) throws Exception
	{
        	if(sql.length() > 0)
		{
            		sql.delete(0, sql.length());
        	}

        	sql.append
        	(
			"SELECT " +
			"LTRIM (a.ayear, '0') AS ayear, a.sms, b.code_name AS csms, COUNT (1) AS take_num " +
			"FROM regt007 a JOIN syst001 b ON a.sms = b.code AND b.kind = 'SMS' " +
			"WHERE 1=1 " +
			"AND a.ayear || a.sms < '" + Utility.nullToSpace(ht.get("AYEAR")) + Utility.nullToSpace(ht.get("SMS")) + "' " +
			"AND a.crsno = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' " +
			"AND a.unqual_take_mk = 'N' " +
			"AND a.untakecrs_mk = 'N' " +
			"AND a.payment_status = '2' " +
			"GROUP BY a.ayear, a.sms, b.code_name " +
			"ORDER BY a.ayear DESC, a.sms DESC "
        	);

        	DBResult rs = null;

        	try
		{
            		if(pageQuery)
			{
                		// 依分頁取出資料
                		rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            		}
			else
			{
                		// 取出所有資料
                		rs = dbmanager.getSimpleResultSet(conn);
                		rs.open();
                		rs.executeQuery(sql.toString());
            		}

			return rs;

        	}
		catch (Exception e)
		{
            		throw e;
        	}
    }

	public Hashtable getDataForPla013m_st(Hashtable ht) throws Exception
	{
		Hashtable rowHt = new Hashtable();

		DBResult rs = null;

		try
		{
			sql.append
			(
				"SELECT " +
				"B.SECTION||A.EXAM_CLASSM_CODE AS G_KEY, " +
				"COUNT(1) AS ST_NUM, A.CRSNO " +
				"FROM REGT007 A " +
				"JOIN EXAT021 B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.CRSNO=B.CRSNO AND B.RESIT_TYPE='1' " +
				"JOIN STUT003 C ON A.STNO=C.STNO " +
				"LEFT JOIN STUT004 S4 ON S4.AYEAR = A.AYEAR AND S4.SMS = A.SMS AND S4.STNO = A.STNO "+
				"JOIN SYST002 D ON D.CENTER_CODE = NVL(S4.CENTER_CODE,C.CENTER_CODE) " +
				"WHERE 1=1 "
			);

			if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			{
				sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			{
				sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			{
				sql.append("AND D.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
			{
				sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
			}

			sql.append("AND TRIM(A.EXAM_CLASSM_CODE) IS NOT NULL ");

			sql.append
			(
				"AND a.unqual_take_mk = 'N' " +
				"AND a.untakecrs_mk = 'N' "+
				"AND a.payment_status > '1' "
			);

			sql.append
			(
				"GROUP BY B.SECTION||A.EXAM_CLASSM_CODE, A.CRSNO " +
				"ORDER BY B.SECTION||A.EXAM_CLASSM_CODE "
			);

			// 取出所有資料
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());
			Hashtable temp = null;
			while (rs.next()) {
				if (rowHt.get(rs.getString("G_KEY")) == null) {
					temp = new Hashtable();
					temp.put(rs.getString("CRSNO"), rs.getString("ST_NUM"));
					rowHt.put(rs.getString("G_KEY"), temp);
				} else {
					temp = (Hashtable) rowHt.get(rs.getString("G_KEY"));
					temp.put(rs.getString("CRSNO"), rs.getString("ST_NUM"));
					rowHt.put(rs.getString("G_KEY"), temp);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)
			{
				rs.close();
			}
		}

		return rowHt;
    }

	public Hashtable getDataForPla013m_class(Hashtable ht) throws Exception
	{
		Hashtable rowHt = new Hashtable();

		DBResult rs = null;

		try
		{
			sql.append
			(
				"SELECT " +
				"A.SECTION||A.EXAM_CLASSM_CODE AS G_KEY, " +
				"COUNT(1) AS CLASS_NUM FROM (" +
				"	SELECT B.SECTION, A.EXAM_CLASSM_CODE, A.MASTER_CLASS_CODE " +
				"	FROM REGT007 A " +
				"	JOIN EXAT021 B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.CRSNO=B.CRSNO AND B.RESIT_TYPE='1' " +
				"	JOIN STUT003 C ON A.STNO=C.STNO " +
				"	JOIN SYST002 D ON C.CENTER_CODE=D.CENTER_CODE " +
				"	WHERE 1=1 "
			);

			if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			{
				sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			{
				sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			{
				sql.append("AND D.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
			{
				sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
			}

			sql.append("AND TRIM(A.EXAM_CLASSM_CODE) IS NOT NULL ");

			sql.append("GROUP BY B.SECTION, A.EXAM_CLASSM_CODE, A.MASTER_CLASS_CODE ");

			sql.append
			(
				") A " +
				"GROUP BY A.SECTION||A.EXAM_CLASSM_CODE " +
				"ORDER BY A.SECTION||A.EXAM_CLASSM_CODE "
			);

			// 取出所有資料
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			while (rs.next())
			{
				rowHt.put(rs.getString("G_KEY"), rs.getString("CLASS_NUM"));
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)
			{
				rs.close();
			}
		}

		return rowHt;
    }

	public Hashtable getDataForPla013m_totalNum(Hashtable ht) throws Exception
	{
		Hashtable rowHt = new Hashtable();

		DBResult rs = null;

		try
		{
			sql.append
			(
				"SELECT " +
				"b.section, COUNT (*) st_num " +
				"FROM regt007 a " +
				"JOIN exat021 b ON a.ayear = b.ayear AND a.sms = b.sms AND a.crsno = b.crsno AND b.resit_type = '1' " +
				"JOIN stut003 c ON a.stno = c.stno " +
				"LEFT JOIN STUT004 S4 ON S4.AYEAR = A.AYEAR AND S4.SMS = A.SMS AND S4.STNO = A.STNO "+
				"JOIN syst002 d ON D.CENTER_CODE = NVL(S4.CENTER_CODE,C.CENTER_CODE) " +
				"WHERE 1=1 "
			);

			if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			{
				sql.append("AND a.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			{
				sql.append("AND a.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			{
				sql.append("AND d.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
			}

			if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
			{
				sql.append("AND a.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
			}

			sql.append
			(
				"AND a.unqual_take_mk = 'N' " +
				"AND a.untakecrs_mk = 'N' "+
				"AND a.PAYMENT_STATUS <> '1' "+
				"AND a.payment_status > '1' "
			);

			sql.append
			(
				"GROUP BY b.section " +
				"ORDER BY b.section "
			);

			// 取出所有資料
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			while (rs.next())
			{
				rowHt.put(rs.getString("SECTION"), rs.getString("ST_NUM"));
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if(rs != null)
			{
				rs.close();
			}
		}

		return rowHt;
    }

	public DBResult getRegt007Cout001Scdt008(Hashtable ht) throws Exception
    {
        Vector      result  =   null;
        DBResult    rs      =   null;

        try
        {
            result  =   new Vector();

            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());

            sql.append
            (
        		"SELECT S04.STNO, S04.AYEAR, S04.SMS,COUNT (CO01.CRSNO) CNTCRS, SUM (CO01.CRD) AS SUMCRS, SC08.AVG_MARK " +
        		"FROM SCDT004 S04 "+
                "JOIN COUT001 CO01 ON 1=1 "+
                "AND S04.AYEAR = CO01.AYEAR " +
        		"AND S04.SMS = CO01.SMS " +
        		"AND S04.CRSNO = CO01.CRSNO " +
                "JOIN SCDT008 SC08 ON 1=1 " +
              	"AND S04.AYEAR = SC08.AYEAR " +
        		"AND S04.SMS = SC08.SMS " +
        		"AND S04.STNO = SC08.STNO " +
        		"WHERE 1 = 1 " +
        		"AND CASE WHEN S04.SMS='3' and S04.FNLMARK =-1 then 'F' "+
                "when S04.sms IN('1','2') and (S04.FNLMARK =-1 and S04.MIDMARK =-1) then 'F'  "+
  		        "else 'T' end ='T' "
            );

            //條件欄位
            String  AYEAR           =   Utility.checkNull(ht.get("AYEAR"), "");             //學年
            String  SMS             =   Utility.checkNull(ht.get("SMS"), "");               //學期
            String  STNO		    =   Utility.checkNull(ht.get("STNO"), "");       		//學號

            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND S04.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND S04.SMS = '" + SMS + "' ");

            //加入條件 - 學號
            if (!"".equals(STNO))
                sql.append("AND S04.STNO = '" + STNO + "' ");

            sql.append("GROUP BY S04.STNO, S04.AYEAR, S04.SMS, SC08.AVG_MARK ");
System.out.println(sql);
            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

	public DBResult getEvalStNum(Hashtable ht) throws Exception
	{
        	if(sql.length() > 0)
		{
            		sql.delete(0, sql.length());
        	}

        	sql.append
        	(
        		"SELECT eval_st_num from ( " +
			"SELECT " +
			"a.ayear, a.sms, COUNT(1) AS eval_st_num " +
			"FROM regt007 a " +
			"WHERE 1=1 " +
			"AND a.ayear || a.sms < '" + Utility.nullToSpace(ht.get("AYEAR")) + Utility.nullToSpace(ht.get("SMS")) + "' " +
			"AND a.crsno = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' " +
			"AND a.unqual_take_mk = 'N' " +
			"AND a.untakecrs_mk = 'N' " +
			"AND a.payment_status = '2' " +
			"GROUP BY a.ayear, a.sms " +
			"ORDER BY a.ayear DESC, a.sms DESC " +
			") WHERE ROWNUM=1 "
        	);

        	DBResult rs = null;

        	try
		{
			if(pageQuery)
			{
				// 依分頁取出資料
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			}
			else
			{
				// 取出所有資料
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			return rs;
        	}
		catch (Exception e)
		{
            		throw e;
        	}
    }

    public void getDataForPla124R(Vector vt, Hashtable ht, String CMPS_KIND) throws Exception {
		DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			String tutOrLab = "";

			if (CMPS_KIND.equals("1") || CMPS_KIND.equals("2"))
			{
				tutOrLab = " a.tut_cmps_code AS cmps_code, a.tut_class_code AS class_code, ";
			}
			else if (CMPS_KIND.equals("3"))
			{
				tutOrLab = " a.lab_cmps_code AS cmps_code, a.lab_class_code AS class_code, ";
			}

			sql.append
			(
				"SELECT  center_abbrname, stno, NAME, email, areacode_office || '-' || tel_office AS office, " +
				"areacode_home || '-' || tel_home AS home, mobile, addr, crsno, " +
				"crs_name, '無編入班級' AS class_code " +
				"FROM (SELECT d.center_abbrname, a.ayear, a.sms, a.stno, g.NAME, a.crsno, j.crs_name, " +
				"	d.center_abrcode, " + tutOrLab +
				"	g.tel_office, " +
				"	g.areacode_office, g.areacode_home, g.tel_home, g.mobile, " +
				"	g.email, (g.crrsaddr_zip || g.crrsaddr) addr, a.UNQUAL_TAKE_MK, a.UNTAKECRS_MK, a.PAYMENT_STATUS " +
				"	FROM regt007 a JOIN stut003 c ON a.stno = c.stno AND c.asys = a.asys " +
				"	JOIN stut002 g ON g.idno = c.idno " +
				"	JOIN syst002 d ON c.center_code = d.center_code "+
				"	JOIN cout002 j ON a.crsno = j.crsno "+
				") WHERE UNQUAL_TAKE_MK='N' AND UNTAKECRS_MK='N' AND PAYMENT_STATUS>'1' "
			);

			sql.append("AND AYEAR = '" + Utility.dbStr(ht.get("AYEAR")) + "' ");

			sql.append("AND SMS = '" + Utility.dbStr(ht.get("SMS")) + "' ");

			sql.append("AND CENTER_ABRCODE = '" + Utility.dbStr(ht.get("CENTER_ABRCODE")) + "' ");

			sql.append("AND CMPS_CODE = '" + Utility.dbStr(ht.get("CMPS_CODE")) + "' ");

        		if(!Utility.nullToSpace(ht.get("CRSNO")).equals(""))
			{
            		sql.append("AND CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
				sql.append("AND (SUBSTR (class_code, 0, 2) <> '"+Utility.dbStr(ht.get("CENTER_ABRCODE"))+Utility.dbStr(ht.get("CMPS_CODE"))+"' OR TRIM (class_code) IS NULL)");
        		}
			else
			{
				sql.append("AND (SUBSTR (class_code, 0, 2) <> '"+Utility.dbStr(ht.get("CENTER_ABRCODE"))+Utility.dbStr(ht.get("CMPS_CODE"))+"' OR TRIM (class_code) IS NULL)");
			}


			sql.append("ORDER BY CRSNO ");

			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				vt.add(rowHt);
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if (rs != null)
				rs.close();
		}
	}


    public DBResult getDataForPla117r(Hashtable ht) throws Exception {
		DBResult rs = null;
		try
		{
			//修改寫法，利用temporary table(寫入資料放在M層) by barry 2008/05/16
			if(sql.length() >0)
				sql.delete(0, sql.length());
			sql.append(
					"SELECT unique y.idno, x.stno, y.center_code FROM nou.regt007 x, nou.stut003 y, nou.syst002 z "+
					"WHERE " +
						"    x.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' " +
						"AND x.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' " +
						"AND z.center_abrcode like '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "%' " +
						"AND x.tut_cmps_code like '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "%' " +
						"AND x.UNQUAL_TAKE_MK='N' " +
						"AND x.UNTAKECRS_MK='N' " +
						"AND x.stno = y.stno " +
						"AND y.center_code = z.center_code "+
						"AND x.stno IN (SELECT unique (a.stno) FROM (tmp_pla117r) a WHERE (SELECT COUNT (unique b.stno) FROM (tmp_pla117r) b WHERE b.center_abrcode = a.center_abrcode AND b.idno = a.idno) > 1) "
			);

			// 表示不含未繳費
			 if(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y"))
				 sql.append("AND x.PAYMENT_STATUS > '1' ");

			 sql.append("ORDER BY y.center_code, y.idno ");

			/* sql.append
			(
				"SELECT DISTINCT y.idno, x.stno FROM regt007 x, stut003 y, syst002 z " +
				"WHERE x.stno = y.stno " +
				"AND y.center_code = z.center_code " +
				"AND x.stno IN (" +
				"SELECT DISTINCT (a.stno) " +
				"FROM (SELECT a4.center_abrcode, a2.idno, a2.stno " +
				"		FROM regt007 a1 JOIN stut003 a2 ON a2.stno = a1.stno " +
				"		JOIN stut002 a3 ON a3.idno = a2.idno " +
				"		JOIN syst002 a4 ON a4.center_code = a2.center_code " +
				"		JOIN plat012 a5 ON a5.ayear = a1.ayear AND a5.sms = a1.sms AND a5.class_code = a1.master_class_code " +
				"WHERE 1 = 1 "
			);

        		if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			{
            		sql.append("AND a1.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        		}

        		if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			{
            		sql.append("AND a1.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        		}

        		if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			{
            		sql.append("AND a4.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
        		}

			sql.append("AND a5.class_kind <> '8' ) a ");

			sql.append("WHERE (SELECT COUNT (DISTINCT b.stno) FROM (SELECT a4.center_abrcode, a2.idno,  a2.stno FROM regt007 a1 JOIN stut003 a2 ON a2.stno = a1.stno JOIN stut002 a3 ON  a3.idno = a2.idno JOIN syst002 a4 ON a4.center_code = a2.center_code JOIN  plat012 a5 ON a5.ayear = a1.ayear AND a5.sms = a1.sms AND a5.class_code =  a1.master_class_code WHERE 1 = 1 ");

        		if(!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
			{
            		sql.append("AND a1.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        		}

        		if(!Utility.nullToSpace(ht.get("SMS")).equals(""))
			{
            		sql.append("AND a1.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        		}

        		if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
			{
            		sql.append("AND a4.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
        		}

			sql.append("AND a5.class_kind <> '8' ) b ");

			sql.append("WHERE b.center_abrcode = a.center_abrcode AND b.idno = a.idno) > 1 ");

			sql.append(") ORDER BY y.idno "); */

			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

		}
		catch(Exception e)
		{
			throw e;
		}
		return rs;
	}

    // 取得某班的學生資料-->用來寄發選取某個學生
    public Vector getStuDataForPER066M(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
					"SELECT DISTINCT b.STNO, d.NAME, c.NOU_EMAIL "+
					"FROM plat012 a, regt007 b, stut003 c, stut002 d "+
					"WHERE "+
					"    a.AYEAR='"+ht.get("AYEAR")+"' " +
					"and a.SMS='"+ht.get("SMS")+"' " +
					"and a.TCH_IDNO='"+ht.get("USER_IDNO")+"' " +
					"and b.UNQUAL_TAKE_MK='N' " +
					"and b.UNTAKECRS_MK='N' " +
					"and b.PAYMENT_STATUS>'1' " +
					"and a.AYEAR=b.AYEAR " +
					"and a.SMS=b.SMS " +
					"and a.CRSNO=b.CRSNO " +
					"and ( (A.CLASS_KIND IN ('1','8') AND a.CLASS_CODE=b.TUT_CLASS_CODE) OR " +
					"      (A.CLASS_KIND IN ('2','3','4') AND A.CLASS_CODE = B.ASS_CLASS_CODE) OR "+
					"      (A.CLASS_KIND IN ('7') AND A.CLASS_CODE = B.LAB_CLASS_CODE) )"+
					"and b.STNO=c.STNO " +
					"and c.IDNO=d.IDNO " +
					"and c.BIRTHDATE=d.BIRTHDATE "
			);

			if(!Utility.nullToSpace(ht.get("CRSNO")).equals(""))
				sql.append("and a.CRSNO='"+ht.get("CRSNO").toString()+"' ");
			if(!Utility.nullToSpace(ht.get("CLASS_CODE")).equals(""))
				sql.append("and a.CLASS_CODE='"+ht.get("CLASS_CODE").toString()+"' ");
			if(!Utility.nullToSpace(ht.get("allStno")).equals(""))
				sql.append("and c.STNO IN ('"+ht.get("allStno").toString().replaceAll(",", "','")+"') ");

			sql.append("ORDER BY b.STNO");
System.out.println("sql:"+sql);
			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}

    public Vector doCheck(Hashtable ht) throws Exception {
		Vector result = new Vector();

		try {
			if (sql.length() > 0)
				sql.delete(0, sql.length());

			String cmpsKind = Utility.nullToSpace(ht.get("CMPS_KIND"));
			String ayear = Utility.nullToSpace(ht.get("AYEAR"));
			String sms = Utility.nullToSpace(ht.get("SMS"));
			String stno = Utility.nullToSpace(ht.get("STNO"));
			String crsno = Utility.nullToSpace(ht.get("CRSNO"));
			String classCode = Utility.nullToSpace(ht.get("CLASS_CODE"));

			String field = "A.TUT_CLASS_CODE";
			if("3".equals(cmpsKind))
				field = "A.LAB_CLASS_CODE";

			sql.append("SELECT COUNT(*) AS COUNT                                                     \n");
			sql.append("FROM REGT007 A WHERE A.AYEAR = '" + ayear + "' AND A.SMS = '" + sms + "' AND A.STNO = '" + stno + "'\n");
			sql.append("AND A.CRSNO != '" + crsno + "' AND " + field + " IS NOT NULL                     \n");
			sql.append("AND SUBSTR(" + field + ", 3, 2) = SUBSTR('" + classCode + "', 3, 2)                  \n");
			sql.append("AND A.UNQUAL_TAKE_MK='N' AND A.UNTAKECRS_MK='N' AND A.PAYMENT_STATUS>'1' ");

			DBResult rs = null;
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			Hashtable rowHt = null;
			while (rs.next()) {
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

// reg020m 不符選課重算繳費金額
//by poto 2008/07/25
    public Vector Reg020MQuery(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());
            String AYEAR =(String)ht.get("AYEAR");
            String SMS =(String)ht.get("SMS");
			sql.append("select ");
			sql.append("s2.IDNO AS IDNO, ");
            sql.append("r7.STNO AS STNO, ");
            sql.append("s3.CENTER_CODE AS CENTER_CODE, ");
            sql.append("r5.TAKE_MANNER AS TAKE_MANNER,  ");
            sql.append("r7.ASYS AS ASYS, ");
            sql.append("s2.NAME AS NAME, ");
            sql.append("r5.PAYABLE_TOTAL_AMT AS PAYABLE_TOTAL_AMT, ");
            sql.append("r10.WRITEOFF_NO AS WRITEOFF_NO, ");
            sql.append("r7.CRSNO AS CRSNO, ");
            sql.append("z.code_name AS REASON, ");
            sql.append("c2.CRS_NAME AS CRS_NAME	  ");
            sql.append("from regt007 r7 ");
            sql.append("join regt010 r10 on 1=1  ");
            sql.append("AND r7.AYEAR = r10.AYEAR ");
            sql.append("AND r7.SMS = r10.SMS ");
            sql.append("AND r7.STNO = r10.STNO ");
            sql.append("AND SUBSTR(r10.WRITEOFF_NO,LENGTH(r10.WRITEOFF_NO)-5,6) = ");
            sql.append("( ");
            sql.append("select MAX(SUBSTR(a.WRITEOFF_NO,LENGTH(a.WRITEOFF_NO)-5,6))  ");
            sql.append("from regt010 a where 1=1  ");
            sql.append("AND r10.AYEAR = a.AYEAR ");
            sql.append("AND r10.SMS = a.SMS ");
            sql.append("AND r10.STNO = a.STNO ");
            sql.append(") ");
            sql.append("join regt005 r5 on 1=1 ");
            sql.append("AND r7.AYEAR = r5.AYEAR ");
            sql.append("AND r7.SMS = r5.SMS ");
            sql.append("AND r7.STNO = r5.STNO ");
            sql.append("join stut003 s3 on r7.STNO = s3.STNO ");
            sql.append("join stut002 s2 on s2.IDNO = s3.IDNO AND s2.BIRTHDATE = s3.BIRTHDATE  ");
            sql.append("join cout002 c2 on r7.CRSNO = c2.CRSNO ");
            sql.append("left join syst001 z on r7.UNQUAL_TAKE_CAUSE = z.code and z.kind ='UNQUAL_TAKE_CAUSE' ");
            sql.append("where r7.AYEAR ='"+AYEAR+"' and r7.SMS ='"+SMS+"' and r7.UNQUAL_TAKE_MK ='Y' ");
			sql.append("ORDER BY r7.STNO");

			DBResult rs = null;
			DBResult rs1 = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++){
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				}
				REGCALCPAYMENT BO = new REGCALCPAYMENT(dbmanager,conn);
    			BO.setASYS(rs.getString("ASYS"));
    			BO.setAYEAR(AYEAR);
    			BO.setSMS(SMS);
    			BO.setSTNO(rs.getString("STNO"));
    			System.out.println(BO.execute());
    			String PAYABLE_TOTAL_AMT = BO.getPAYABLE_TOTAL_AMT();
                rowHt.put("TOTAL_FEE",PAYABLE_TOTAL_AMT);


				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}


// reg022m 不符選課重算繳費金額
//by poto 2008/08/19
    public Vector getReg022m_export(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());
            String AYEAR =(String)ht.get("AYEAR");
            String SMS =(String)ht.get("SMS");
            String TYPE =  com.acer.util.Utility.checkNull(ht.get("TYPE"), "");
            /*
            TPYE = 0  全部
                   1  監獄生
                   2  專班生
                   3  非監獄生 非專班生
            */
            if("0".equals(TYPE)){

            }else if("1".equals(TYPE)){
                 sql.append("SELECT DISTINCT STUT003.STNO AS STNO ,STUT003.CENTER_CODE AS CENTER_CODE,REGT007.TUT_CMPS_CODE AS TUT_CMPS_CODE  ");
                 sql.append("FROM REGT007  ");
                 sql.append("JOIN STUT003 ON STUT003.STNO =REGT007.STNO  ");
                 sql.append("JOIN STUT002 ON STUT003.IDNO =STUT002.IDNO AND STUT003.BIRTHDATE =STUT002.BIRTHDATE ");
                 sql.append("JOIN SYST002 ON STUT003.CENTER_CODE = SYST002.CENTER_CODE  ");
                 sql.append("WHERE 1=1  ");
                 sql.append("AND UNQUAL_TAKE_MK ='N'  ");
                 sql.append("AND UNTAKECRS_MK ='N' ");
                 sql.append("AND AYEAR = '"+AYEAR+"' ");
                 sql.append("AND SMS ='"+SMS+"' ");
                 sql.append("AND TUT_CMPS_CODE IN (SELECT CMPS_CODE FROM PLAT002 A WHERE A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.CMPS_NAME LIKE '%監獄%' AND SYST002.CENTER_ABRCODE = A.CENTER_ABRCODE ) ");
                 sql.append("AND LAB_CMPS_CODE IN (SELECT CMPS_CODE FROM PLAT002 B WHERE B.AYEAR ='"+AYEAR+"' AND B.SMS ='"+SMS+"' AND B.CMPS_NAME LIKE '%監獄%' AND SYST002.CENTER_ABRCODE = B.CENTER_ABRCODE ) ");
                 sql.append("GROUP BY STUT003.STNO,STUT003.CENTER_CODE,REGT007.TUT_CMPS_CODE ");
                 sql.append("ORDER BY STUT003.CENTER_CODE||REGT007.TUT_CMPS_CODE||STUT003.STNO  ");
            }else if("2".equals(TYPE)){
                 sql.append("SELECT DISTINCT STUT003.STNO AS STNO ,STUT003.CENTER_CODE AS CENTER_CODE,REGT007.TUT_CMPS_CODE AS TUT_CMPS_CODE  ");
                 sql.append("FROM REGT007  ");
                 sql.append("JOIN STUT003 ON STUT003.STNO =REGT007.STNO  ");
                 sql.append("JOIN STUT002 ON STUT003.IDNO =STUT002.IDNO AND STUT003.BIRTHDATE =STUT002.BIRTHDATE ");
                 sql.append("JOIN SYST002 ON STUT003.CENTER_CODE = SYST002.CENTER_CODE  ");
                 sql.append("WHERE 1=1  ");
                 sql.append("AND UNQUAL_TAKE_MK ='N'  ");
                 sql.append("AND UNTAKECRS_MK ='N' ");
                 sql.append("AND AYEAR = '"+AYEAR+"' ");
                 sql.append("AND SMS ='"+SMS+"' ");
                 sql.append("AND TUT_CMPS_CODE IN (SELECT CMPS_CODE FROM PLAT002 A WHERE A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.CMPS_NAME LIKE '%專班%' AND SYST002.CENTER_ABRCODE = A.CENTER_ABRCODE ) ");
                 sql.append("AND LAB_CMPS_CODE IN (SELECT CMPS_CODE FROM PLAT002 B WHERE B.AYEAR ='"+AYEAR+"' AND B.SMS ='"+SMS+"' AND B.CMPS_NAME LIKE '%專班%' AND SYST002.CENTER_ABRCODE = B.CENTER_ABRCODE ) ");
                 sql.append("GROUP BY STUT003.STNO,STUT003.CENTER_CODE,REGT007.TUT_CMPS_CODE ");
                 sql.append("ORDER BY STUT003.CENTER_CODE||REGT007.TUT_CMPS_CODE||STUT003.STNO  ");
            }else if("3".equals(TYPE)){
                 sql.append("SELECT DISTINCT STUT003.STNO AS STNO, RPAD(NVL(STUT002.CRRSADDR_ZIP,'00000'),5,'0') AS CRRSADDR_ZIP ");
                 sql.append("FROM REGT007 ");
                 sql.append("JOIN STUT003 ON STUT003.STNO =REGT007.STNO ");
                 sql.append("JOIN STUT002 ON STUT003.IDNO =STUT002.IDNO AND STUT003.BIRTHDATE =STUT002.BIRTHDATE  ");
                 sql.append("JOIN SYST002 ON STUT003.CENTER_CODE = SYST002.CENTER_CODE ");
                 sql.append("WHERE 1=1 ");
                 sql.append("AND UNQUAL_TAKE_MK ='N' ");
                 sql.append("AND UNTAKECRS_MK ='N' ");
                 sql.append("AND AYEAR = '"+AYEAR+"' ");
                 sql.append("AND SMS ='"+SMS+"' ");
                 //sql.append("AND CENTER_CODE !='14' ");
                 //sql.append("AND CENTER_CODE !='12' ");
                 sql.append("AND TUT_CMPS_CODE IN (SELECT CMPS_CODE FROM PLAT002 A WHERE A.AYEAR ='"+AYEAR+"' AND A.SMS ='"+SMS+"' AND A.CMPS_NAME NOT LIKE '%監獄%' AND A.CMPS_NAME NOT LIKE '%專班%' AND SYST002.CENTER_ABRCODE = A.CENTER_ABRCODE) ");
                 sql.append("GROUP BY STUT003.STNO,STUT002.CRRSADDR_ZIP  ");
                 sql.append("ORDER BY RPAD(NVL(STUT002.CRRSADDR_ZIP,'00000'),5,'0')||STUT003.STNO  ");
            }




			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++){
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				}
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}

   // 取得某班的學生資料-->用來寄發選取某個學生
    public Vector getReg025mQuery(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append("select ");
			sql.append("r5.AYEAR AS AYEAR,r5.SMS AS SMS, sys1.CODE_NAME AS SMS_NAME, r7.UNQUAL_TAKE_MK, r7.UNTAKECRS_MK, s01.code_name, ");
			sql.append("r5.stno AS STNO, ");
    		sql.append("s2.IDNO AS IDNO, ");
    		sql.append("s2.BIRTHDATE AS BIRTHDATE, ");
    		sql.append("c2.crs_name AS CRS_NAME, ");
    		sql.append("s2.name AS NAME, ");
			sql.append("s3.CENTER_CODE AS CENTER_CODE, ");
			sql.append("s02.CENTER_ABBRNAME AS CENTER_ABBRNAME, ");
			sql.append("c2.crd AS CRD, ");
			sql.append("case when TO_CHAR(SYSDATE,'YYYYMMDD') >= r1.AFTER_TAKE_SDATE then 'Y' else 'N' end as AFTER_TAKE_CHECK , ");
			sql.append("r7.CRSNO, TAKE_DATE, TAKE_TIME, TAKE_DATE||TAKE_TIME AS TAKE_DATE_TIME, TUT_CMPS_CODE, LAB_CMPS_CODE, r7.PAYMENT_STATUS, ALLOCATE_CMPS_CODE, MASTER_CLASS_CODE, TUT_CLASS_CODE, LAB_CLASS_CODE, EXAM_CLASSM_CODE, ASS_CLASS_CODE, EXAM_SEAT, LAB_SEAT, TUT_SEAT, VOLNUTEER_TIME01, VOLNUTEER_TIME02, VOLNUTEER_TIME03, VOLNUTEER_TIME04, VOLNUTEER_TIME05, VOLNUTEER_TIME06, VOLNUTEER_TIME07, VOLNUTEER_TIME08, VOLNUTEER_TIME09, VOLNUTEER_TIME10, S_CLASS_TYPE, ");
			sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.TUT_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS TUT_CMPS_NAME,");
            sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.LAB_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS LAB_CMPS_NAME,");
            sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.ALLOCATE_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS ALLOCATE_CMPS_NAME ");
            sql.append("from regt005 r5 ");
            sql.append("join regt007 r7 on r5.ayear =r7.ayear and r5.sms =r7.sms and r5.stno =r7.stno ");
            sql.append("join regt001 r1 on r1.ayear =r7.ayear and r1.sms =r7.sms and r1.asys =r7.asys ");
            sql.append("join stut003 s3 on r7.stno = s3.stno ");
            sql.append("join stut002 s2 on s2.idno = s3.idno  and s2.birthdate = s3.birthdate ");
            sql.append("join cout002 c2 on r7.crsno = c2.crsno  ");
            sql.append("join syst002 s02 on s02.CENTER_CODE = s3.CENTER_CODE  ");
			sql.append("join syst001 s01 on s01.KIND='PAYMENT_STATUS' and s01.code=r7.PAYMENT_STATUS ");
			sql.append("join syst001 sys1 on sys1.kind = 'SMS' and sys1.code = r5.sms ");
            sql.append("where 1=1  ");
            sql.append("and r5.TAKE_ABNDN ='N' ");
            sql.append("and r7.UNQUAL_TAKE_MK ='N'  ");
            sql.append("and r7.UNTAKECRS_MK ='N' ");
			if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")){
				sql.append("and r5.AYEAR='"+ht.get("AYEAR").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("SMS")).equals("")){
				sql.append("and r5.SMS='"+ht.get("SMS").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("STNO")).equals("")){
				sql.append("and r5.STNO = '"+ht.get("STNO").toString()+"' ");
			}
            if(!Utility.nullToSpace(ht.get("BIRTHDATE")).equals("")){
				sql.append("and s3.BIRTHDATE = '"+ht.get("BIRTHDATE").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("IDNO")).equals("")){
				sql.append("and s3.IDNO = '"+ht.get("IDNO").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("CENTER_CODE")).equals("")){
				sql.append("and s3.CENTER_CODE = '"+ht.get("CENTER_CODE").toString()+"' ");
			}
			sql.append("ORDER BY s3.CENTER_CODE,r5.STNO,r7.CRSNO ");

			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}

 // 取得某班的學生資料-->用來寄發選取某個學生
    public Vector getReg024mQuery(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append("select ");
			sql.append("r5.AYEAR AS AYEAR,r5.SMS AS SMS, r7.UNQUAL_TAKE_MK, r7.UNTAKECRS_MK, s01.code_name, ");
			sql.append("r5.stno AS STNO, ");
    		sql.append("s2.IDNO AS IDNO, ");
    		sql.append("s2.BIRTHDATE AS BIRTHDATE, ");
    		sql.append("c2.crs_name AS CRS_NAME, ");
    		sql.append("s2.name AS NAME, ");
			sql.append("nvl(s04.center_code,s3.CENTER_CODE) AS CENTER_CODE, ");
			sql.append("s02.CENTER_ABBRNAME AS CENTER_ABBRNAME, ");
			sql.append("c2.crd AS CRD, ");
			sql.append("r7.CRSNO, TAKE_DATE, TAKE_TIME, TUT_CMPS_CODE, LAB_CMPS_CODE, r7.PAYMENT_STATUS, ALLOCATE_CMPS_CODE, MASTER_CLASS_CODE, TUT_CLASS_CODE, LAB_CLASS_CODE, EXAM_CLASSM_CODE, ASS_CLASS_CODE, EXAM_SEAT, LAB_SEAT, TUT_SEAT, VOLNUTEER_TIME01, VOLNUTEER_TIME02, VOLNUTEER_TIME03, VOLNUTEER_TIME04, VOLNUTEER_TIME05, VOLNUTEER_TIME06, VOLNUTEER_TIME07, VOLNUTEER_TIME08, VOLNUTEER_TIME09, VOLNUTEER_TIME10, S_CLASS_TYPE, ");
			sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.TUT_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS TUT_CMPS_NAME,");
            sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.LAB_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS LAB_CMPS_NAME,");
            sql.append("(select CMPS_NAME from plat002 a where a.ayear =r7.ayear and a.sms =r7.sms and r7.ALLOCATE_CMPS_CODE = a.CMPS_CODE and s02.CENTER_ABRCODE = a.CENTER_ABRCODE) AS ALLOCATE_CMPS_NAME ");
            sql.append("from regt005 r5 ");
            sql.append("join regt007 r7 on r5.ayear =r7.ayear and r5.sms =r7.sms and r5.stno =r7.stno ");
            sql.append("join stut003 s3 on r7.stno = s3.stno ");
            sql.append("LEFT JOIN STUT004 S04 ON S04.AYEAR = r5.AYEAR AND S04.SMS = r5.SMS AND S04.STNO = r5.STNO ");
            sql.append("join stut002 s2 on s2.idno = s3.idno  and s2.birthdate = s3.birthdate ");
            sql.append("join cout002 c2 on r7.crsno = c2.crsno  ");
            sql.append("join syst002 s02 on s02.CENTER_CODE = nvl(s04.center_code,s3.CENTER_CODE)  ");
			sql.append("join syst001 s01 on s01.KIND='PAYMENT_STATUS' and s01.code=r7.PAYMENT_STATUS ");
            sql.append("where 1=1  ");
			if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")){
				sql.append("and r5.AYEAR='"+ht.get("AYEAR").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("SMS")).equals("")){
				sql.append("and r5.SMS='"+ht.get("SMS").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("STNO")).equals("")){
				sql.append("and r5.STNO = '"+ht.get("STNO").toString()+"' ");
			}
            if(!Utility.nullToSpace(ht.get("BIRTHDATE")).equals("")){
				sql.append("and s3.BIRTHDATE = '"+ht.get("BIRTHDATE").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("IDNO")).equals("")){
				sql.append("and s3.IDNO = '"+ht.get("IDNO").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("CENTER_CODE")).equals("")){
				sql.append("and s02.CENTER_CODE = '"+ht.get("CENTER_CODE").toString()+"' ");
			}
			sql.append("ORDER BY r7.CRSNO ");

			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}


 // 取得某班的學生資料-->用來寄發選取某個學生
    public Vector getReg160r(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append("select STNO,CRSNO ,'' CCS_RMK, '已修' SCD_RMK \n");
            sql.append("from regt007 a \n");
            sql.append("where  \n");
            sql.append("(STNO,CRSNO) IN (  \n");
            sql.append("select STNO,CRSNO  \n");
            sql.append("from scdt004 where  ayear||sms!='0972' and CRSNO_SMSGPA>=60 and trim(old_stno) is null  and a.STNO = STNO and a.CRSNO = CRSNO \n");
            sql.append(")  \n");
            sql.append("and  a.ayear ='097'  and a.sms ='2'  \n");
            sql.append("union  \n");
            sql.append("select a.STNO,a.CRSNO,'' CCS_RMK, '已修' SCD_RMK \n");
            sql.append("from regt007 a   \n");
            sql.append("join cout010 b on a.CRSNO = b.CRSNO  \n");
            sql.append("where    \n");
            sql.append("(STNO,CRSNO_OLD) IN (   \n");
            sql.append("select STNO,CRSNO AS CRSNO_OLD from scdt004 where  ayear||sms!='0972' and CRSNO_SMSGPA>=60  and trim(old_stno) is null and a.STNO = STNO and a.CRSNO = CRSNO  \n");
            sql.append(") \n");
            sql.append("and a.ayear ='097'  and a.sms ='2'  \n");
            sql.append("union \n");
            sql.append("select STNO,CRSNO ,'已抵' CCS_RMK, '' SCD_RMK \n");
            sql.append("from regt007 a \n");
            sql.append("where  \n");
            sql.append("(STNO,CRSNO) IN (  \n");
            sql.append("select STNO,CRSNO   \n");
            sql.append("from ccst003  where trim(old_stno) is null   and a.STNO = STNO and a.CRSNO = CRSNO \n");
            sql.append(")  \n");
            sql.append("and  a.ayear ='097'  and a.sms ='2'  \n");
            sql.append("union  \n");
            sql.append("select a.STNO,a.CRSNO,'已抵' CCS_RMK, '' SCD_RMK \n");
            sql.append("from regt007 a   \n");
            sql.append("join cout010 b on a.CRSNO = b.CRSNO  \n");
            sql.append("where    \n");
            sql.append("(STNO,CRSNO_OLD) IN (   \n");
            sql.append("select STNO,CRSNO AS CRSNO_OLD from ccst003 where trim(old_stno) is null and a.STNO = STNO and a.CRSNO = CRSNO \n");
            sql.append(")  \n");
            sql.append("and a.ayear ='097'  and a.sms ='2' \n");

			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}

    // 在分配校區時,將所需要的資訊寫入暫存的DB中
    public void inputTempDataToPlat027(Hashtable ht, String commonCondition) throws Exception {
    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
				"SELECT A.CRSNO,A.STNO " +
				"FROM REGT007 A " +
					"JOIN STUT003 B ON B.STNO=A.STNO "+
					"JOIN SYST002 C ON C.CENTER_CODE=B.CENTER_CODE AND C.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
				"WHERE "+
					"    A.AYEAR='"+ht.get("AYEAR")+"' "+
					"AND A.SMS='"+ht.get("SMS")+"' "+
					"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
					"AND A.UNQUAL_TAKE_MK='N' "+
					"AND A.UNTAKECRS_MK='N' "+
					"AND A.S_CLASS_TYPE IS NULL "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			// 批次insert
			PreparedStatement ps = conn.prepareStatement
			(
				"INSERT INTO PLAT027(AYEAR,SMS,CRSNO,STNO,CENTER_ABRCODE,TUT_CMPS_CODE) "+
				"VALUES('"+ht.get("AYEAR")+"', '"+ht.get("SMS")+"', ?, ?, '"+ht.get("CENTER_ABRCODE")+"', '"+ht.get("CMPS_CODE_MAX")+"')"
			);

			// 每500筆insert一次
			int insertCount = 1;
			while (rs.next())
			{
				ps.setString(1, rs.getString("CRSNO")); // 科目
				ps.setString(2, rs.getString("STNO")); // 學號

				// 每500筆執行一次
				if(insertCount%500==0)
					ps.executeBatch();

				ps.addBatch();
				insertCount++;
			}
			ps.executeBatch();
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}
	}

    //  在分配校區時,取得最多人選課的科目
    public String getMaxStuCrsno(Hashtable ht) throws Exception {
    	String result = "";

    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
					"SELECT A.CRSNO, MAX(CRSNO_STU_NUM) " +
					"FROM PLAT027 A " +
						// 需超過所設定的人數上限的科目才進行分配,每科有多少人選課
						"JOIN "+
							"(" +
								"SELECT COUNT(1) AS CRSNO_STU_NUM, B.CRSNO " +
								"FROM PLAT027 B " +
							   	"WHERE "+
							   		"    B.AYEAR='"+ht.get("AYEAR")+"' "+
									"AND B.SMS='"+ht.get("SMS")+"' "+
									"AND B.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+  // 主要要劃分的校區
									"AND B.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
									"AND A.IS_ALLOCATE='N' "+ // 尚未分配的人數
								"GROUP BY D.CRSNO "+
							") G ON A.CRSNO=G.CRSNO AND G.CRSNO_STU_NUM>"+ht.get("")+" "+
					"WHERE "+
						"    A.AYEAR='"+ht.get("AYEAR")+"' "+
						"AND A.SMS='"+ht.get("SMS")+"' "+
						"AND A.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
						"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
						"AND A.IS_ALLOCATE='N' "+ // 尚未分配的人數
					"GROUP BY A.CRSNO "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			if (rs.next())
				result = rs.getString("CRSNO");
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}

		return result;
	}

    //  在分配校區時,取得在某個科目下所有學生學生的所有選課科目,由選課科目少的開始排序
    // dataType:從主要校區找(first)   從次要校區找(second)
    public Vector getStuChoiceCrsno(Hashtable ht, String crsno, String dataType) throws Exception {
    	Vector result = new Vector();

    	String queryType = dataType.equals("first")?"N":"Y";

    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
					"SELECT A.STNO, A.CRSNO, " +
						// 該生所選的科目數
						"(SELECT COUNT(1) FROM PLAT027 B WHERE B.AYEAR='"+ht.get("AYEAR")+"' AND B.SMS='"+ht.get("SMS")+"' AND B.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' AND B.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' AND B.STNO=A.STNO) AS STU_CRSNO_COUNT " +
					"FROM PLAT027 A " +
					"WHERE "+
						"    A.AYEAR='"+ht.get("AYEAR")+"' "+
						"AND A.SMS='"+ht.get("SMS")+"' "+
						"AND A.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
						"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
						"AND A.IS_ALLOCATE='"+queryType+"' "+
						// 有選擇該科的學生
						"AND A.STNO IN (SELECT C.STNO FROM PLAT027 C WHERE C.AYEAR='"+ht.get("AYEAR")+"' AND C.SMS='"+ht.get("SMS")+"' AND C.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' AND C.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' AND C.CRSNO='"+crsno+"' AND C.IS_ALLOCATE='"+queryType+"') "+
					"ORDER BY STU_CRSNO_COUNT, A.STNO "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			Vector tmp = new Vector();
			while (rs.next())
			{
				Hashtable rowHt = new Hashtable();
				rowHt.put("STNO", rs.getString("STNO"));
				rowHt.put("CRSNO", rs.getString("CRSNO"));

				tmp.add(rowHt);
			}

			// 將資料整理成  學號---->所選的科目1,所選的科目2,....
			Vector pk = new Vector();
			pk.add("STNO");

			UtilityX.combinCompareNextTheSameData(pk, "CRSNO", ",", tmp, result);
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}

		return result;
	}

    //  在分配校區時,取得每各科目的選課人數---為了取得最多人選課的科目
    public Vector getEachCrsnoStuNum(Hashtable ht) throws Exception {
    	Vector result = new Vector();

    	DBResult rs = null;
		try
		{
			int num = Integer.parseInt(ht.get("NUM").toString()); // 區分人數

			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
					"SELECT A.CRSNO, count(1) as EACH_CRSNO_STU_NUM " +
					"FROM PLAT027 A " +
					"WHERE "+
						"    A.AYEAR='"+ht.get("AYEAR")+"' "+
						"AND A.SMS='"+ht.get("SMS")+"' "+
						"AND A.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
						"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
						"AND A.IS_EXECUTE='N' "+  // 僅取得尚未處理過的科目,有處理過的,整個科目都會是Y
					"GROUP BY CRSNO "+
					"HAVING count(1)>="+num+" "+
					"ORDER BY EACH_CRSNO_STU_NUM DESC" // 由大排到小
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			// 如不超過所設定要分配的人數則不列入考慮
			if(rs.next()){
				Hashtable content = new Hashtable();
				content.put("CRSNO", rs.getString("CRSNO"));
				content.put("EACH_CRSNO_STU_NUM", rs.getString("EACH_CRSNO_STU_NUM"));

				result.add(content);
			}
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}

		return result;
	}

    //  在分配校區時,取得每各科目的選課人數,dataType:取得原校區(first)/次校區(second)的所有選課人數
    public Hashtable getALLCrsnoStuNum(Hashtable ht, String dataType) throws Exception {
    	Hashtable result = new Hashtable();

    	String queryType = dataType.equals("first")?"N":"Y"; // M:原校區的選課資料   Y:次校區的選課人數

    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
					"SELECT A.CRSNO, count(1) as EACH_CRSNO_STU_NUM " +
					"FROM PLAT027 A " +
					"WHERE "+
						"    A.AYEAR='"+ht.get("AYEAR")+"' "+
						"AND A.SMS='"+ht.get("SMS")+"' "+
						"AND A.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
						"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
						"AND A.IS_ALLOCATE='"+queryType+"' "+
					//	"AND A.IS_EXECUTE='"+queryType+"' "+
					"GROUP BY CRSNO "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			while (rs.next()){
				result.put(rs.getString("CRSNO"),rs.getString("EACH_CRSNO_STU_NUM"));
			}
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}

		return result;
	}

    // 在分配校區時,取得分配過去的人數,是否有科目不到最低人數,如有則用以下的方式來處理
    // 目的:使次校區的選課人數每一科至少都大於最低人數
    // 方式:1.從原校區移動部分學生使次校區的學生人數超過人數下限(一科一科移過去)
    //     2.當無法從原校區移動過去時,則只好將次校區該科學生移動到原校區去
    public void checkAllocate(Hashtable ht, String commonCondition) throws Exception {
    	DBResult rs = null;
		try
		{
			REGT007GATEWAY regt007 = new REGT007GATEWAY(dbmanager, conn);
			Hashtable firstCmps = regt007.getALLCrsnoStuNum(ht, "first"); //取得目前原校區每一科的選課人數
			Hashtable secondCmps = regt007.getALLCrsnoStuNum(ht, "second"); //取得目前次校區每一科的選課人數

			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
				"SELECT A.CRSNO, count(1) as EACH_CRSNO_STU_NUM " +
					"FROM PLAT027 A " +
				"WHERE "+
					"    A.AYEAR='"+ht.get("AYEAR")+"' "+
					"AND A.SMS='"+ht.get("SMS")+"' "+
					"AND A.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
					"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
					"AND A.IS_ALLOCATE='Y' "+ // 已分配的人數
				"GROUP BY CRSNO "+
				"HAVING COUNT(1)<"+ht.get("LOWER_NUM").toString()
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			while (rs.next()){
				// 如該科在原校區無選課資料,或是選課人數為0則不進行檢核,直接進行下一科
				if(Utility.checkNull(firstCmps.get(rs.getString("CRSNO")),"0").equals("0"))
					continue;

				// CHECK1:至原校區找學生跟該科的學生對調,看是否能降低這樣的情況(對調後,不論是原校區/次校區對於移動學生的選課科目的選課人數須超過最低人數)
				boolean check1Result = this.checkChangeStu1(ht, rs.getString("CRSNO"), commonCondition, firstCmps, secondCmps);

				//CHECK2:當CHECK1無法從原校區移動學生過來到次校區時,則將次校區該科的學生移動到原校區去
				if(!check1Result)
					this.checkChangeStu2(ht, rs.getString("CRSNO"), commonCondition, firstCmps, secondCmps);
			}
			rs.close();

			// CHECK3 有些科目會因為CHECK2原本剛好符合最低人數的條件,變成沒有,因此需再做一次CHECK1
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			firstCmps = regt007.getALLCrsnoStuNum(ht, "first"); //取得目前原校區每一科的選課人數
			secondCmps = regt007.getALLCrsnoStuNum(ht, "second"); //取得目前次校區每一科的選課人數
			while (rs.next()){
				// 如該科在原校區無選課資料,或是選課人數為0則不進行檢核,直接進行下一科
				if(Utility.checkNull(firstCmps.get(rs.getString("CRSNO")),"0").equals("0"))
					continue;

				// CHECK1:至原校區找學生跟該科的學生對調,看是否能降低這樣的情況(對調後,不論是原校區/次校區對於移動學生的選課科目的選課人數須超過最低人數)
				this.checkChangeStu1(ht, rs.getString("CRSNO"), commonCondition, firstCmps, secondCmps);
			}
			rs.close();
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}
	}

    // 細部判斷!
    // 參數:ht:前端的查詢條件,crsno:要處理的科目(次校區中,選課人數低於人數下限), firstCmps:原校區中所有每一科的選課人數  secondCmps:次校區中所有每一科的選課人數
    // 1.判斷該科目在原校區是否有學生,如有學生是否可從原校區移動學生過來值至該科在次校區滿足最低人數
    // 2.如上面的方式無法達成,則只能移動次校區的學生到原校區去-->但有可能影響次校區其他科目的選課人數會低於最低人數,此時要用此判斷
    // 當次校區滿足最低人數以上(或是全數移動到原校區)則回傳true,表示不需再往下繼續判斷
    public boolean checkChangeStu1(Hashtable ht, String crsno, String condition, Hashtable firstCmps, Hashtable secondCmps) throws Exception {
    	boolean result = false;

    	DBResult rs = null;
		try
		{
			int lowNum = Integer.parseInt(ht.get("LOWER_NUM").toString()); // 人數下限
			int needNum = lowNum-Integer.parseInt(secondCmps.get(crsno).toString()); // 次校區中所需人數(該科人數與人數下限的差)

			// 在次校區中有選該科學生的所有選課資料--從原校區中移動學生到次校區去
			REGT007GATEWAY regt007 = new REGT007GATEWAY(dbmanager, conn);
			Vector thisCrsnoStus = regt007.getStuChoiceCrsno(ht, crsno, "first");

			// 一個迴圈表示一個學生的所有選課資料
			String moveStno = "''";
			int moveStuNum = 0; // 移動的人數,僅需移動needNum人即可
			for(int i=0; i<thisCrsnoStus.size(); i++){
				Hashtable stu = (Hashtable)thisCrsnoStus.get(i);
				String thisStu = stu.get("STNO").toString();
				String[] choiceCrsnos = Utility.split(stu.get("CRSNO").toString(),","); // 該生所選擇的所有課,多科則用逗號隔開

				// 開始判斷如該生移到另外一個校區時,是否對所選擇的科目會有影響(即小於所設定的人數下限)
				int leaglCrsnoCount = 0; // 合乎標準的科目數,與所選課的科目相同時,才代表如該生轉移到另一個校區,對原校區的科目沒影響
				for(int j=0; j<choiceCrsnos.length; j++){
					String choiceCrsno = choiceCrsnos[j]; // 該生所選擇的某一科目
					int firstCmpsStuNum = Integer.parseInt(firstCmps.get(choiceCrsno).toString());//該科目在原校區的選課人數
					int secondCmpsStuNum = Integer.parseInt(Utility.checkNull(secondCmps.get(choiceCrsno),"0"));//該科目在次校區的選課人數,次校區有可能沒有該科(因要原校區有的科目次校區不一定會有)

					// 如移動目前判斷這位學生,只要該學生所選的科目中,因移動後,
					// 1.次校區並無該生所選的科目 2.導致原校區的某一科低於人數下限,3.次校區加了這個人之後還是小於人數下限(除目前正在處理的這科之外) 則直接跳離判斷,該學生不能移動到次校區
					if(secondCmpsStuNum==0||firstCmpsStuNum-1<lowNum||(secondCmpsStuNum+1<lowNum&&choiceCrsno.equals(crsno)))
						j=choiceCrsnos.length;
					else
						leaglCrsnoCount++;
				}

				// 如該生轉移到其他校區原校區的科目均大於最低人數時,則進行轉移
				if(leaglCrsnoCount==choiceCrsnos.length){
					// 該生在原校區所選擇的科目人數-1
					for(int j=0; j<choiceCrsnos.length; j++){
						String choiceCrsno = choiceCrsnos[j]; // 該生所選擇的科目
						String eachCrsnoStuNum = firstCmps.get(choiceCrsno).toString();//該科目的選課人數(在該校區,已扣除轉移到其他校區的學生)
						firstCmps.put(choiceCrsno, ((Integer.parseInt(eachCrsnoStuNum)-1)<=0?"0":(Integer.parseInt(eachCrsnoStuNum)-1)+""));
					}

					// STEP5.2.2 將該生紀錄起來
					moveStno+=",'"+thisStu+"'";
					moveStuNum++;
				}

				// 表示移動到次校區的人數已足夠
				if(moveStuNum==needNum){
					result = true;
					break;
				}
			}

			// 將移動過去的學生進行註記
			if(result){
				PLAT027DAO plat027update = new PLAT027DAO(dbmanager, conn);
				plat027update.setIS_ALLOCATE("Y");
				plat027update.update(condition+"AND STNO IN ("+moveStno+")");
			}
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}

		return result;
	}

    //  細部判斷!
    // 參數:ht:前端的查詢條件,crsno:要處理的科目(次校區中,選課人數低於人數下限), firstCmps:原校區中所有每一科的選課人數  secondCmps:次校區中所有每一科的選課人數
    // 1.判斷該科目在原校區是否有學生,如有學生是否可從原校區移動學生過來值至該科在次校區滿足最低人數
    // 2.如上面的方式無法達成,則只能移動次校區的學生到原校區去-->但有可能影響次校區其他科目的選課人數會低於最低人數,此時要用此判斷
    // 當次校區滿足最低人數以上(或是全數移動到原校區)則回傳true,表示不需再往下繼續判斷
    public void checkChangeStu2(Hashtable ht, String crsno, String condition, Hashtable firstCmps, Hashtable secondCmps) throws Exception {
		try
		{
			// 在次校區中有選該科學生的所有選課資料--從原校區中移動學生到次校區去
			REGT007GATEWAY regt007 = new REGT007GATEWAY(dbmanager, conn);
			Vector thisCrsnoStus = regt007.getStuChoiceCrsno(ht, crsno, "second");

			// 一個迴圈表示一個學生的所有選課資料
			String moveStno = "''";
			for(int i=0; i<thisCrsnoStus.size(); i++){
				moveStno+=",'"+((Hashtable)thisCrsnoStus.get(i)).get("STNO").toString()+"'";
			}

			// 將移動過去的學生進行註記
			PLAT027DAO plat027update = new PLAT027DAO(dbmanager, conn);
			plat027update.setIS_ALLOCATE("N");
			plat027update.update(condition+"AND STNO IN ("+moveStno+")");
		}
		catch(Exception e)
		{
			throw e;
		}finally{

		}
	}

    //  在分配校區時,將該中心的主校區的所有學生全數轉到次要校區---PLAT027的合併按鈕
    public void updateAllDataToPlat027(Hashtable ht) throws Exception {
    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			// 需超過所設定的人數上限的科目才進行分配
			sql.append(
				"SELECT DISTINCT A.STNO " +
				"FROM REGT007 A " +
					"JOIN STUT003 B ON B.STNO=A.STNO "+
					"JOIN SYST002 C ON C.CENTER_CODE=B.CENTER_CODE AND C.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
				"WHERE "+
					"    A.AYEAR='"+ht.get("AYEAR")+"' "+
					"AND A.SMS='"+ht.get("SMS")+"' "+
					"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "+  // 主要要劃分的校區
					"AND A.UNQUAL_TAKE_MK='N' "+
					"AND A.UNTAKECRS_MK='N' "+
					"AND A.S_CLASS_TYPE IS NULL "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			// 批次insert
			PreparedStatement ps = conn.prepareStatement
			(
				"UPDATE REGT007 SET TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MIN")+"',ALLOCATE_CMPS_CODE='"+ht.get("CMPS_CODE_MIN")+"'  "+
				"WHERE AYEAR='"+ht.get("AYEAR")+"' AND SMS='"+ht.get("SMS")+"' AND STNO=? AND TUT_CMPS_CODE='"+ht.get("CMPS_CODE_MAX")+"' "
			);

			// 每500筆insert一次
			int insertCount = 1;
			while (rs.next())
			{
				ps.setString(1, rs.getString("STNO")); // 科目

				// 每500筆執行一次
				if(insertCount%500==0)
					ps.executeBatch();

				ps.addBatch();
				insertCount++;
			}
			ps.executeBatch();
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}
	}

    // 剛開始做試算的時候,當主校區的所有科目的選課人數小於區分人數時,均移動到次校區去
    public void changeCmpsToPlat027(Hashtable ht, String commonCondition) throws Exception {
    	DBResult rs = null;
		try
		{
			// 當有科目要移動到次校區時,連帶著要再考慮所移動過去後該生原本所選的科目是否有變成小於區分人數,進而又要再移動一次,
			// 因此要一直判斷,直到沒資料為止
			int num = Integer.parseInt(ht.get("NUM").toString());// 所設定的區分人數
			while(true){
				if(sql.length() >0)
					sql.delete(0, sql.length());

				// 需超過所設定的人數上限的科目才進行分配
				sql.append(
					"SELECT COUNT(1) AS CRSNO_COUNT, CRSNO " +
					"FROM PLAT027 " +
					"WHERE "+commonCondition+
						"AND IS_ALLOCATE='N' "+ // Y表示已移到次校區
						"AND IS_EXECUTE='N' "+// 表示尚未處理過該科的資料
					"GROUP BY CRSNO "+
					"HAVING COUNT(1)<"+num  // 僅處理小於區分人數的科目
				);

				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());

				BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("SQL:"+sql.toString());
				buf.readLine();

				String totalRemoveCrsno = "''";  // 總共要移動的科目
				while(rs.next()){
					totalRemoveCrsno+=",'"+rs.getString("CRSNO")+"'";
				}
				rs.close();

				System.out.print(totalRemoveCrsno);
				buf.readLine();

				// 表示沒有要移過去的科目了
				if(totalRemoveCrsno.equals("''"))
					break;

				// 至PLAT027中去找這些科目的所有學生,並將這些學生所選擇的所有科目均變更到次校區去
				PLAT027DAO plat027 = new PLAT027DAO(dbmanager, conn);
				plat027.setResultColumn("DISTINCT STNO");
				plat027.setWhere(commonCondition+"AND CRSNO IN ("+totalRemoveCrsno+")");
				rs = plat027.query();

				System.out.print("SELECT DISTINCT STNO FROM PLAT027 WHERE"+ commonCondition+"AND CRSNO IN ("+totalRemoveCrsno+")");
				buf.readLine();

				// 批次UPDATE
				PreparedStatement ps = conn.prepareStatement("UPDATE PLAT027 SET IS_ALLOCATE='Y', IS_EXECUTE='Y' WHERE "+commonCondition+" AND STNO=?");

				int executeCount = 1;// 每500筆update一次
				while (rs.next())
				{
					ps.setString(1, rs.getString("STNO")); // 科目

					// 每500筆執行一次
					if(executeCount%500==0)
						ps.executeBatch();

					ps.addBatch();
					executeCount++;
				}
				rs.close();
				ps.executeBatch();

				dbmanager.commit();

				System.out.print(executeCount);
				buf.readLine();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}
	}

    public static void main(String[] args) throws Exception {
    	com.acer.log.MyLogger logger = new com.acer.log.MyLogger("PLAGETCLSDATA");
    	DBManager dbManager = new DBManager(logger);
    	Connection	conn	=	dbManager.getConnection(AUTCONNECT.mapConnect("PLA", "0"));

        Hashtable content = new Hashtable();
        content.put("AYEAR", "097");
        content.put("SMS", "1");
        content.put("ASYS", "1");
        content.put("STNO", "922204965");
        content.put("MIN_PT_CRS_CNT", "18");
        content.put("MIN_PT_CRD_CNT", "1");
        content.put("MIN_MARK_AVG", "70");

		// STEP1:將所需的資料全都寫到暫存的table(PLAT027)中
		REGT007GATEWAY regt007 = new REGT007GATEWAY(dbManager, conn);
		regt007.getRegt007Cout001Scdt008(content);

		System.out.println("ok!!");
    }

    public DBResult getStuChoiceCrs(Hashtable otherDataHt) throws Exception {
		DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			String AYEAR = otherDataHt.get("AYEAR").toString();
			String SMS = otherDataHt.get("SMS").toString();
			String STNO = otherDataHt.get("STNO").toString();
			String YN = otherDataHt.get("YN").toString();
			String AMOUNT = otherDataHt.get("AMOUNT").toString();
			String WRITEOFF_NO = otherDataHt.get("WRITEOFF_NO").toString();
			String IDNO = otherDataHt.get("IDNO").toString();
			String USER_ID = otherDataHt.get("USER_ID").toString();

			sql.append
			(
				"SELECT a.ayear, a.sms, a.crsno, B.CRS_NAME||DECODE(A.UNQUAL_TAKE_MK,'Y','('||C.CODE_NAME||')','') AS CRS_NAME,a.stno AS STNO, " +
				"'"+YN+"' AS YN, '"+AMOUNT+"' as AMOUNT, '"+WRITEOFF_NO+"' as WRITEOFF_NO, '"+IDNO+"' as IDNO, '"+USER_ID+"' as USER_ID, "+
				"DECODE(D.CLASS_YN,'Y','',DECODE(A.SMS,'3','暑期課程無面授',DECODE(A.S_CLASS_TYPE,'','　自選面授方式：'||DECODE(A.STU_TEACHING_TYPE,'',DECODE(A.MASTER_CLASS_CODE,'@0@0','視訊夜間班', '@0@1','視訊下午班', '@0@2','視訊上午班','實體面授班'), '@0@1','視訊下午班；【因未達開班人數，改編入視訊夜間班】', '@0@2','視訊上午班；【因未達開班人數，改編入視訊夜間班】','1','實體面授班；【因未達開班人數，改編入視訊夜間班】' ),'　自選面授方式：專班'))) AS STU_TEACHING_TYPE "+
				"FROM regt007 a "+
				"JOIN COUT002 B ON B.CRSNO=A.CRSNO "+
				"LEFT JOIN SYST001 C ON C.CODE=A.UNQUAL_TAKE_CAUSE AND C.KIND='UNQUAL_TAKE_CAUSE' "+
				"LEFT JOIN PLAT028 D ON D.AYEAR = A.AYEAR AND D.SMS = A.SMS "+
				"WHERE "+
				"    a.AYEAR='"+AYEAR+"' "+
				"AND a.SMS='"+SMS+"' "+
				"AND a.STNO='"+STNO+"' "+
				"AND a.UNTAKECRS_MK='N' "+
				"ORDER BY a.CRSNO "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{

		}

		return rs;
	}

    // PLA008M---LOAD DATA,該某中心的某個校區的學生的班級資料清空
    public void updateRegt007ForPla008m(Hashtable ht) throws Exception {
    	DBResult rs = null;
		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
				"SELECT DISTINCT A.STNO, A.CRSNO, D.CLASS_KIND " +
				"FROM REGT007 A " +
					"JOIN STUT003 B ON B.STNO=A.STNO "+
					"JOIN SYST002 C ON C.CENTER_CODE=B.CENTER_CODE AND C.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' "+
					// 一般面授不update
					"JOIN PLAT009 D ON D.AYEAR=A.AYEAR AND D.SMS=A.SMS AND D.CMPS_CODE=A.TUT_CMPS_CODE AND D.CRSNO=A.CRSNO AND D.CENTER_ABRCODE=C.CENTER_ABRCODE AND D.CLASS_KIND>'1' "+
				"WHERE "+
					"    A.AYEAR='"+ht.get("AYEAR")+"' "+
					"AND A.SMS='"+ht.get("SMS")+"' "+
					"AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE")+"' "+  // 主要要劃分的校區
					"AND A.UNQUAL_TAKE_MK='N' "+
					"AND A.UNTAKECRS_MK='N' "+
					"AND A.S_CLASS_TYPE IS NULL "
			);

			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

			// 批次update
			PreparedStatement ps = conn.prepareStatement
			(
					"UPDATE REGT007 SET MASTER_CLASS_CODE=?, TUT_CLASS_CODE=?, EXAM_CLASSM_CODE=?, ASS_CLASS_CODE=?, "+
					" PLA_UPD_DATE = TO_CHAR(SYSDATE,'YYYYMMDD'), "+
					" PLA_UPD_TIME = TO_CHAR(SYSDATE,'HH24mmss'), "+
					" PLA_UPD_USER_ID = '"+(String)ht.get("USER_ID")+"', "+
					" PLA_ROWSTAMP = TO_CHAR(SYSDATE,'HH24mmsssss') "+
					"WHERE AYEAR='"+ht.get("AYEAR")+"' AND SMS='"+ht.get("SMS")+"' AND CRSNO=? AND STNO=? "
			);

			// 每500筆update一次
			int insertCount = 1;
			while (rs.next())
			{
				String classCodeTmp = "";  // 依照班級類別還原預設值,如class_kind=6的時候則清空
				String classKind = rs.getString("CLASS_KIND");

				// 不為網路班或是6則換下筆
				if(!(classKind.equals("2")||classKind.equals("6")))
					continue;

				// 網路班
				if(classKind.equals("2"))
					classCodeTmp = ht.get("NETWK_CLASS_NAME").toString();

				ps.setString(1, classCodeTmp);
				ps.setString(2, classCodeTmp);
				ps.setString(3, classCodeTmp);
				ps.setString(4, classCodeTmp);
				ps.setString(5, rs.getString("CRSNO"));
				ps.setString(6, rs.getString("STNO")); // 學號

				// 每500筆執行一次
				if(insertCount%500==0)
					ps.executeBatch();

				ps.addBatch();
				insertCount++;
			}
			ps.executeBatch();
		}
		catch(Exception e)
		{
			throw e;
		}finally{
			if(rs!=null)
				rs.close();
		}
	}


	 /**
     *  科目 REGT007 總人數
     */
    public String ALL_STD_NUM(Hashtable ht) throws Exception {
        if(ht == null) {
            ht = new Hashtable();
        }
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        String NUM = "0";
        String AYEAR =(String)ht.get("AYEAR");
		String SMS =(String)ht.get("SMS");
		String CRSNO =(String)ht.get("CRSNO");
        sql.append("SELECT COUNT(A.STNO) AS NUM ");
		sql.append("FROM REGT007 A ");
		sql.append("JOIN STUT003 B ON A.STNO = B.STNO ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND UNQUAL_TAKE_MK = 'N' AND UNTAKECRS_MK = 'N' ");
		sql.append("AND A.AYEAR = '"+AYEAR+"' AND A.SMS = '"+SMS+"' ");
		sql.append("AND B.ENROLL_STATUS IN ('1','2','8') ");
		sql.append("AND A.CRSNO = '"+CRSNO+"' ");
        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = " " + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }
        Vector result = new Vector();
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
               NUM = rs.getString("NUM");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
            
        }
        return NUM;
    }

    /**
     *  取得電腦實習材料費選修人科
     */
    public String getLabStuNumForPcs110r(Hashtable ht) throws Exception {
    	String result = "";

        if(sql.length() > 0)
            sql.delete(0, sql.length());
        String condition = "";
        if(!Utility.nullToSpace(ht.get("PRT_MANNER")).equals("")) {
            String PRT_MANNER =  Utility.nullToSpace(ht.get("PRT_MANNER"));
            //1 繳費前 2繳費後
            if("2".equals(PRT_MANNER)){
            	condition ="AND A.PAYMENT_STATUS IN ('2','3','4') \n";
            }
        }
        
        sql.append(
        	"SELECT COUNT(1) as CHOICE_STU_NUM \n" +
        	"FROM \n" +
        	"( \n" +
	        	"SELECT A.AYEAR,A.SMS,A.ASYS,A.STNO,B.CRSNO \n" +
	        	"FROM REGT007 A    \n" +
	        	"JOIN COUT001 B ON  A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.CRSNO=B.CRSNO AND B.LAB_TIMES > 0 \n" +
	        	"WHERE 1=1 \n" +
	        		condition+
	        		"AND A.AYEAR = '"+ht.get("AYEAR")+"' \n" +
	        		"AND A.SMS = '"+ht.get("SMS")+"' \n" +
	        		"AND A.ASYS ='"+ht.get("ASYS")+"' \n" +
	        		"AND A.unqual_take_mk='N' \n" +
	        		"AND A.UNTAKECRS_MK = 'N' \n" +
	        ")");
	        		
        
        

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            if (rs.next())
            	result = rs.getString("CHOICE_STU_NUM");

        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

    /**
     *  取得電腦實習材料費選修人科之總應收金額
     */
    public String getLabTotalPayableAmtForPcs110r(Hashtable ht) throws Exception {
    	String result = "0";

        if(sql.length() > 0)
            sql.delete(0, sql.length());
        String condition = "";
        if(!Utility.nullToSpace(ht.get("PRT_MANNER")).equals("")) {
            String PRT_MANNER =  Utility.nullToSpace(ht.get("PRT_MANNER"));
            //1 繳費前 2繳費後
            if("2".equals(PRT_MANNER)){
            	condition ="AND B.PAYMENT_STATUS IN ('2','3','4') \n";
            }
        }
        String sql =
        	"SELECT NVL(SUM(LAB_FEE),'0') AS TOTAL_LAB_FEE " +
        	"FROM" +
        	"( " +
        		"SELECT A.AYEAR,A.SMS,A.ASYS,A.STNO,A.LAB_FEE,writeoff_no  "+
        		"FROM REGT010 A JOIN REGT005 B ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND  A.STNO=B.STNO "+
        		"WHERE 1=1 " +
        			condition+
        			"AND b.TAKE_ABNDN='N' " +
        			"AND NVL(SUBSTR(A.WRITEOFF_NO,7),0)=(select MAX(NVL(SUBSTR(R10.WRITEOFF_NO,7),0)) FROM REGT010 R10 WHERE R10.AYEAR =A.AYEAR AND R10.SMS =A.SMS AND R10.STNO =A.STNO AND R10.ABNDN_ORDER = 'N' ) " +
        	") "+
        	"WHERE " +
        		"AYEAR = '"+ht.get("AYEAR")+"' " +
        		"AND SMS = '"+ht.get("SMS")+"' " +
        		"AND ASYS ='"+ht.get("ASYS")+"' " +
        		"AND LAB_FEE <> 0 ";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            if (rs.next())
            	result = rs.getString("TOTAL_LAB_FEE");

        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

    /**
     *  取得某一學年期某個學生的選課資料
     */
    public Vector getStuChoiceCrs(String ayear, String sms, String stno) throws Exception {
    	Vector result = new Vector();

        if(sql.length() > 0)
            sql.delete(0, sql.length());

        String sql =
        	"SELECT A.STNO, A.CRSNO, B.CRS_NAME, " +
        		"(SELECT SUM(D.CRD) FROM REGT007 C, COUT002 D WHERE C.AYEAR='"+ayear+"' AND C.SMS='"+sms+"' AND C.UNQUAL_TAKE_MK='N' AND C.UNTAKECRS_MK='N' AND C.STNO=A.STNO AND C.CRSNO=D.CRSNO) AS CRD " +
        	"FROM REGT007 A, COUT002 B "+
        	"WHERE " +
        	"    A.AYEAR='"+ayear+"' "+
        	"AND A.SMS='"+sms+"' "+
        	"AND A.STNO IN ('"+stno.replaceAll(",", "','")+"') "+
        	"AND A.UNQUAL_TAKE_MK='N' "+
        	"AND A.UNTAKECRS_MK='N' "+
        	"AND B.CRSNO=A.CRSNO "+
        	"ORDER BY A.STNO ";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            Vector tmp = new Vector();
            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                tmp.add(rowHt);
            }

            // 將同一個學生弄成一筆資料,多個科目時中間用逗號隔開
            Vector pk = new Vector();
            pk.add("STNO");

            UtilityX.combinCompareNextTheSameData(pk, "CRS_NAME", ",", tmp, result);
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

    /**
     *  取得某一學年期某種班級類型的的學生資料,並將同一個學生的所有選課資料組合起來成為一筆資料<====已編班的部份
     */
    public Vector getQueryHasPlaForPla016m(Hashtable ht, Hashtable cmpsHt) throws Exception {
    	Vector result = new Vector();

    	String[] displayClassTypeArray = {"","MASTER_CLASS_CODE","TUT_CLASS_CODE","LAB_CLASS_CODE","ASS_CLASS_CODE","EXAM_CLASSM_CODE"};
        String ayear = ht.get("AYEAR").toString();
        String sms = ht.get("SMS").toString();
        String centerAbrcode = ht.get("CENTER_ABRCODE").toString();
        String cmpsCode = ht.get("CMPS_CODE").toString();
        String displayClassType = displayClassTypeArray[Integer.parseInt(ht.get("PLA_TYPE").toString())]; // 頁面上顯示的班級代碼(regt007的班級)
        String isDisplayNonPaymentStu = ht.get("PAYMENT_STATUS").toString(); // 是否顯示未繳費的學生  N:不顯示未繳費 Y:顯示
        String crsno = ht.get("CRSNO").toString();
        String displayCrsNameLength = ht.get("CHAR_NUM").toString().equals("")?"4":ht.get("CHAR_NUM").toString(); // 如選擇查詢科目中文時,顯示科目名稱多少字
        String classKind = ht.get("CLASS_KIND").toString();
        String cmpsKind = cmpsHt.get("CMPS_KIND").toString().equals("3")?"LAB_CMPS_CODE":"TUT_CMPS_CODE";

        String sql =
        	// 為了取得學生選課最大數(要組上面的title用),因此多下一層
        	"SELECT MAX(X.STU_COUNT) OVER () AS MAX_STU_COUNT, X.* "+
        	"FROM "+
        	"("+
	        	// 在PLAT012中除面授班級類型外,其餘如網路面授班,均會有兩筆相同的資料(僅CLASS_KIND不同),但僅需顯示一筆,因此用DISTINCT
	        	"SELECT DISTINCT B.STNO,F.NAME,B.CRSNO||'-'||SUBSTR(C.CRS_NAME,1,"+displayCrsNameLength+")||'-'||B."+displayClassType+" AS DETAIL_DATA," +
	        	"    B.CRSNO,COUNT(DISTINCT B.CRSNO) OVER (PARTITION BY B.STNO) AS STU_COUNT "+
		        "FROM PLAT012 A "+
		        "    JOIN REGT007 B ON B.AYEAR=A.AYEAR AND B.SMS=A.SMS AND B.CRSNO=A.CRSNO  "+
		        "        AND B."+cmpsKind+"=A.CMPS_CODE "+
		        // 網路面授/遠距面授是用PLAT012.CLASS_CODE=REGT007.ASS_CLASS_CODE  其它均用PLAT012.CLASS_CODE=REGT007.TUT_CLASS_CODE
		        "        AND CASE WHEN A.CLASS_KIND IN ('2','4') THEN B.ASS_CLASS_CODE "+
		        "                 ELSE B.TUT_CLASS_CODE "+
		        "            END = A.CLASS_CODE "+
		        // 判斷是否顯示未繳費的學生
		        "        AND B.UNQUAL_TAKE_MK='N' AND B.UNTAKECRS_MK='N' "+(isDisplayNonPaymentStu.equals("Y")?"AND B.PAYMENT_STATUS>'1' ":"")+
		        "    JOIN COUT002 C ON C.CRSNO=A.CRSNO "+
		        "    JOIN SYST002 D ON D.CENTER_ABRCODE=A.CENTER_ABRCODE "+
		        "    JOIN STUT003 E ON E.STNO=B.STNO AND E.CENTER_CODE=D.CENTER_CODE "+
		        "    JOIN STUT002 F ON F.IDNO=E.IDNO AND F.BIRTHDATE=E.BIRTHDATE "+
		        "WHERE "+
		        "        A.AYEAR='"+ayear+"' "+
		        "    AND A.SMS='"+sms+"' "+
		        "    AND A.CENTER_ABRCODE='"+centerAbrcode+"' "+
		        "    AND A.CMPS_CODE='"+cmpsCode+"' "+
		        "    AND A.CLASS_KIND<='4' "+
		        (!crsno.equals("")?"AND A.CRSNO='"+crsno+"' ":"")+
		        (!classKind.equals("")?"AND A.CLASS_KIND='"+classKind+"' ":"")+
		        "ORDER BY B.STNO, B.CRSNO "+
		     ") X ";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

    /**
     *  取得某一學年期未編班的的學生資料,並將同一個學生的所有選課資料組合起來成為一筆資料<====編班的部份
     */
    public Vector getQueryNonPlaForPla016m(Hashtable ht, Hashtable cmpsHt) throws Exception {
    	Vector result = new Vector();

    	String[] displayClassTypeArray = {"","MASTER_CLASS_CODE","TUT_CLASS_CODE","LAB_CLASS_CODE","ASS_CLASS_CODE","EXAM_CLASSM_CODE"};
        String ayear = ht.get("AYEAR").toString();
        String sms = ht.get("SMS").toString();
        String centerAbrcode = ht.get("CENTER_ABRCODE").toString();
        String cmpsCode = ht.get("CMPS_CODE").toString();
        String displayClassType = displayClassTypeArray[Integer.parseInt(ht.get("PLA_TYPE").toString())]; // 頁面上顯示的班級代碼(regt007的班級)
        String isDisplayNonPaymentStu = ht.get("PAYMENT_STATUS").toString(); // 是否顯示未繳費的學生  N:不顯示未繳費 Y:顯示
        String crsno = Utility.nullToSpace(ht.get("CRSNO"));
        String displayCrsNameLength = ht.get("CHAR_NUM")==null?"99":(ht.get("CHAR_NUM").toString().equals("")?"4":ht.get("CHAR_NUM").toString()); // 如選擇查詢科目中文時,顯示科目名稱多少字
        String cmpsKind = cmpsCode.toString().equals("")?"":(cmpsHt.get("CMPS_KIND").toString().equals("3")?"LAB_CMPS_CODE":"TUT_CMPS_CODE");
        
        String sql =
        	"SELECT MAX(X.STU_COUNT) OVER () AS MAX_STU_COUNT, X.* "+
        	"FROM "+
        	"( "+
        	"    SELECT B.STNO,E.NAME,B.CRSNO||'-'||SUBSTR(C.CRS_NAME,1,"+displayCrsNameLength+")||'-'||B."+displayClassType+" AS DETAIL_DATA, " +
        	"        B.CRSNO,COUNT(1) OVER (PARTITION BY B.STNO) AS STU_COUNT,F.CENTER_ABBRNAME AS CENTER_ABBRNAME, H.CMPS_NAME, G.REMARK "+
        	"    FROM REGT007 B  "+
        	"        JOIN COUT002 C ON C.CRSNO=B.CRSNO "+
        	"        JOIN STUT003 D ON D.STNO=B.STNO "+
        	"        JOIN STUT002 E ON E.IDNO=D.IDNO AND E.BIRTHDATE=D.BIRTHDATE "+
        	"        JOIN SYST002 F ON F.CENTER_CODE=D.CENTER_CODE "+
        	"        LEFT JOIN PLAT032 G ON B.AYEAR=G.AYEAR AND B.SMS=G.SMS AND B.STNO=G.STNO "+
        	"		 LEFT JOIN PLAT002 H ON B.AYEAR = H.AYEAR AND B.SMS = H.SMS AND B.TUT_CMPS_CODE = H.CMPS_CODE AND F.CENTER_ABRCODE = H.CENTER_ABRCODE  "+
        	"    WHERE "+
        	"            B.AYEAR='"+ayear+"' "+
        	"        AND B.SMS='"+sms+"' "+
        	(!crsno.equals("")?"AND B.CRSNO='"+crsno+"' ":"")+
        	(!cmpsKind.equals("")?"AND B."+cmpsKind+"='"+cmpsCode+"' ":"")+ // 校區
        	(!centerAbrcode.equals("")?"AND F.CENTER_ABRCODE='"+centerAbrcode+"' ":"")+ // 中心
        	"        AND B.UNQUAL_TAKE_MK = 'N'   "+
        	"        AND B.UNTAKECRS_MK = 'N'   "+
        	(isDisplayNonPaymentStu.equals("Y")?"AND B.PAYMENT_STATUS > '1' ":"")+
        	"        AND B.MASTER_CLASS_CODE IS NULL "+
        	"        AND B.TUT_CLASS_CODE IS NULL "+
        	"        AND B.LAB_CLASS_CODE IS NULL "+
        	"        AND B.ASS_CLASS_CODE IS NULL "+
        	"        AND B.EXAM_CLASSM_CODE IS NULL "+
        	"    ORDER BY B.STNO, B.CRSNO "+
        	") X ";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }
    
    /**
     *  取得問卷調查資料....學生首頁用
     *  by poto 20120614 修改可以丟科目給他
     */
    public Vector getCqrDataForStuIndex(Hashtable ht) throws Exception {
    	Vector result = new Vector();    	
    	    	
		String sql = "";
		if(!"SCD019M".equals(ht.get("PROG_CODE"))){
			sql += 
			  "SELECT  DISTINCT                                                                                                                                                         "
			+ "        R1.USER_ID,M.AYEAR,                                                                                                                                                         "
	        +"        TO_CHAR(M.SMS) AS SMS,                                                                                                                                           "
			+ "        to_char(M.ID_TYPE) AS ID_TYPE,"
	        +"        R4.CODE_NAME AS ASSIGN_ID_TYPE,                                                                                                                                  "
	        +"        M.QR_CODE,                                                                                                                                                       "
	        +"        TO_CHAR('000000') AS CRSNO,                                                                                                                                      "
			+ "        TO_CHAR('000000') AS CLASS_CODE, "
	        +"        R2.QSTNNR_NAME,                                                                                                                                                  "
			+ "        M.OPEN_DATE || '~' || M.COLSE_DATE AS IN_DATE,                                                                                                                    "
					+ "         NVL((SELECT COUNT(1) FROM CQRT006 A WHERE A.AYEAR = M.AYEAR AND A.SMS = M.SMS AND A.QR_CODE = M.QR_CODE AND A.ID_TYPE = M.ID_TYPE AND A.CRSNO = '000000' AND (A.STNO = '"
					+ ht.get("STNO")
					+ "' OR A.STNO = '"
					+ ht.get("NOUSTNO")
					+ "')),0) AS IS_WRITE "
			+ "  FROM CQRT009 M                                                                                                                                                         "
			+"  JOIN TABLE(ASSIGN_ID_TYPE_PKG.GET_ASSIGN_ID_TYPE_DATA('"+ ht.get("STNO")+ "', M.ID_TYPE, nvl(M.GRAD_FACULTY_CODE, '%'), '%', M.AYEAR, M.SMS, M.GRAD_S_YEARS, M.GRAD_E_YEARS)) R1  "
			+"    ON R1.ASSIGN_ID_TYPE = M.ID_TYPE                                                                                                                                     "
			+"  JOIN CQRT001 R2                                                                                                                                                        "
			+"    ON R2.QR_CODE = M.QR_CODE                                                                                                                                            "
			+"   AND R2.QR_TYPE = '2'                                                                                                                                                  "
			+"  JOIN SYST001 R4                                                                                                                                                        "
			+"    ON R4.KIND = 'ASSIGN_ID_TYPE'                                                                                                                                        "
			+"   AND R4.CODE = R1.ASSIGN_ID_TYPE                                                                                                                                       "
			+" WHERE M.OPEN_DATE <= TO_CHAR(SYSDATE, 'YYYYMMDD')                                                                                                                       "
			+"   AND M.COLSE_DATE >= TO_CHAR(SYSDATE, 'YYYYMMDD')                                                                                                                      "
			+ "   AND (M.WRITE_TYPE IN ('1','3') OR M.WRITE_TYPE IS NULL) "
			+"UNION ";
		}		
				
				
		sql += 
			 "SELECT  DISTINCT                                                                                                                      "
			+ "        R1.USER_ID,M.AYEAR,                                                                                                                      "
	        +"        TO_CHAR(M.SMS) AS SMS,                                                                                                        "
			+ "        R1.ASSIGN_ID_TYPE AS ID_TYPE,"
	        +"        R4.CODE_NAME AS ASSIGN_ID_TYPE,                                                                                               "
	        +"        M.QR_CODE,                                                                                                                    "
	        +"        TO_CHAR(M.CRSNO) AS CRSNO,                                                                                                    "
			+ "        TO_CHAR(R1.CLASS_CODE) AS CLASS_CODE, "
			+ "        decode(R1.ASSIGN_ID_TYPE,'A',R6.CLS_NAME || '/' || R2.QSTNNR_NAME,R5.CRS_NAME || '/' || TO_CHAR(R1.CLASS_CODE) || '/' || R2.QSTNNR_NAME) AS QSTNNR_NAME, "
			+ "        M.OPEN_DATE || '~' || M.COLSE_DATE AS IN_DATE,                                                                                 "
			//+ "        NVL((SELECT COUNT(1) FROM CQRT006 A WHERE A.AYEAR = M.AYEAR AND A.SMS = M.SMS AND A.QR_CODE = M.QR_CODE AND A.ID_TYPE = R2.ASSIGN_ID_TYPE AND A.CRSNO = M.CRSNO AND A  R1.CLASS_CODE AND A.STNO = '"
			+ "        NVL((SELECT COUNT(1) FROM CQRT006 A WHERE A.AYEAR = M.AYEAR AND A.SMS = M.SMS AND A.QR_CODE = M.QR_CODE AND A.ID_TYPE = R2.ASSIGN_ID_TYPE AND A.CRSNO = M.CRSNO AND A.STNO = '"
			+ ht.get("STNO")
				+ "' OR A.STNO = '"
				+ ht.get("NOUSTNO")
				+ "'),0) AS IS_WRITE "
			+ "  FROM CQRT005 M                                                                                                                      "
			+"  JOIN CQRT001 R2                                                                                                                     "
			+"    ON R2.QR_CODE = M.QR_CODE                                                                                                         "
			+"   AND R2.QR_TYPE = '1'                                                                                                               "
			+"  JOIN TABLE(ASSIGN_ID_TYPE_PKG.GET_ASSIGN_ID_TYPE_DATA('"+ ht.get("STNO")+ "', R2.ASSIGN_ID_TYPE, '%', M.CRSNO, M.AYEAR, M.SMS, '%', '%')) R1   "
			+"    ON R1.ASSIGN_ID_TYPE = R2.ASSIGN_ID_TYPE                                                                                          "
			+"  JOIN SYST001 R4                                                                                                                     "
			+"    ON R4.KIND = 'ASSIGN_ID_TYPE'                                                                                                     "
			+"   AND R4.CODE = R1.ASSIGN_ID_TYPE                                                                                                    "
			+"LEFT JOIN COUT002 R5 ON R5.CRSNO = M.CRSNO "
			+"LEFT JOIN POPT101 R6 ON R6.CLSNO = M.CRSNO AND R6.YEAR = M.AYEAR AND R6.SEASON = M.SMS "
			+ " WHERE (M.WRITE_TYPE IN ('1','3') OR M.WRITE_TYPE IS NULL)  AND M.OPEN_DATE <= TO_CHAR(SYSDATE, 'YYYYMMDD')                                                                                    "
			+ "   AND M.COLSE_DATE >= TO_CHAR(SYSDATE, 'YYYYMMDD')                                                                                   ";

        if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
			sql = sql + " and m.CRSNO = '"
					+ Utility.nullToSpace(ht.get("CRSNO")) + "' ";
        }
        
        System.out.println(sql.toString());
        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    } 

    public Vector getReg107rCrsno(Hashtable ht) throws Exception {
    	
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        sql.append(
        		"SELECT UNIQUE B.CRSNO,B.CRS_NAME,B.CRD "+ 
        		"FROM REGT007 A " +
        		"JOIN COUT002 B ON A.CRSNO=B.CRSNO "+
        		"WHERE 1=1 "
        );
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

   public Vector getReg189rCrsno(Hashtable ht) throws Exception {
    	
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        sql.append(
        		"SELECT UNIQUE B.CRSNO,B.CRS_NAME,B.CRD "+ 
        		"FROM REGT007 A " +
        		"JOIN COUT002 B ON A.CRSNO=B.CRSNO "+
        		"WHERE 1=1 "
        );
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals("")) {
            sql.append("AND SUBSTR(A.TUT_CLASS_CODE,1,1) = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' \n");
        }
		if(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")) {
            sql.append("AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' \n");
        }
		 sql.append("ORDER BY B.CRSNO " );
        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    } 
    
    /**
     *  取得某一學年期未編班的的學生資料,一個學生一科一筆資料<====編班的部份
     */
    public Vector getQueryNonPlaForPla032m_2(Hashtable ht) throws Exception {
    	Vector result = new Vector();
    	
    	DBResult rs = null;
    	try{
	    	// 因中心有可能沒有輸入,故取得要顯示的中心
	    	Vector centerCondition = getCenterVt(ht);
	    	for(int i=0; i<centerCondition.size(); i++){	    		
	    		// 判斷是否有輸入校區,有輸入則直接查詢該校區,否則一個校區一個校區查詢(效能關係...這樣比較快)
	    		Vector cmpsCondition = getCmpsCondition(ht, centerCondition.get(i).toString());
	    		
	    		// 跑兩次,一次面授校區,一次實習校區    	
	    		for(int j=0; j<cmpsCondition.size(); j++){
	    			// 表示無該校區種類
	    			if(cmpsCondition.get(j).toString().equals(""))
	    				continue;
	    			
	    			String cmpsCodeName = j==0?"TUT_CMPS_CODE":"LAB_CMPS_CODE";	    			
	    			String[] cmpsCode = Utility.split(cmpsCondition.get(j).toString(), ","); 
	    			// 一次跑一個校區
	    			for(int k=0; k<cmpsCode.length; k++){
			    		String sql = 
			    			"\n"+
			    			"SELECT B.AYEAR, B.SMS, B.STNO,E.NAME,B.CRSNO,C.CRS_NAME,"+(i==0?"NVL(B.TUT_CLASS_CODE,'')":"NVL(B.LAB_CLASS_CODE,'')")+" AS CLASS_CODE, \n"+
			            	"       F.CENTER_ABBRNAME, H.CMPS_NAME, G.REMARK, F.CENTER_ABRCODE, B."+cmpsCodeName+" AS CMPS_CODE \n"+
			            	"FROM REGT007 B  \n"+
			            	"JOIN COUT002 C ON C.CRSNO=B.CRSNO \n"+
			            	"JOIN STUT003 D ON D.STNO=B.STNO \n"+
			            	"JOIN STUT002 E ON E.IDNO=D.IDNO AND E.BIRTHDATE=D.BIRTHDATE \n"+
			            	"JOIN SYST002 F ON F.CENTER_CODE=D.CENTER_CODE \n"+
			            	"LEFT JOIN PLAT032 G ON B.AYEAR=G.AYEAR AND B.SMS=G.SMS AND G.CENTER_ABRCODE=F.CENTER_ABRCODE AND G.CMPS_CODE=B."+cmpsCodeName+" AND B.STNO=G.STNO AND B.CRSNO=G.CRSNO \n"+
			            	"JOIN PLAT002 H ON B.AYEAR = H.AYEAR AND B.SMS = H.SMS AND B."+cmpsCodeName+" = H.CMPS_CODE AND F.CENTER_ABRCODE = H.CENTER_ABRCODE \n"+
			            	"WHERE \n"+
			            	"            B.AYEAR='"+ht.get("AYEAR")+"' \n"+
			            	"AND B.SMS='"+ht.get("SMS")+"' \n"+
			            	"AND B."+cmpsCodeName+"='"+cmpsCode[k]+"' \n"+ // 校區
			            	"AND F.CENTER_ABRCODE='"+centerCondition.get(i)+"' \n"+    	
			            	"AND B.UNQUAL_TAKE_MK = 'N' \n"+
			            	"AND B.UNTAKECRS_MK = 'N' \n"+
			            	"AND B.PAYMENT_STATUS > '1' \n"+
			            	"AND B."+cmpsCodeName+" IS NOT NULL \n"+			            	
			            	"AND B.MASTER_CLASS_CODE IS NULL \n"+
			            	"AND (LENGTH(B.TUT_CLASS_CODE)<4 OR B.TUT_CLASS_CODE IS NULL) \n"+
			            	"AND B.LAB_CLASS_CODE IS NULL \n"+
			            	"AND B.ASS_CLASS_CODE IS NULL \n"+
			            	"AND B.EXAM_CLASSM_CODE IS NULL \n"+
			            	"ORDER BY B.STNO \n";

			    		rs = dbmanager.getSimpleResultSet(conn);
			            rs.open();
			            rs.executeQuery(sql.toString());
			    		
			            while (rs.next()) {
			            	Hashtable rowHt = new Hashtable();
			                /** 將欄位抄一份過去 */
			                for (int x = 1; x <= rs.getColumnCount(); x++)
			                    rowHt.put(rs.getColumnName(x), rs.getString(x));

			                result.add(rowHt);
			            }
			            rs.close();
	    			}
	    		}
	    	}	    		    
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}finally{
    		if(rs!=null)
    			rs.close();
    	}
    	
        return result;
    }
    
    
    /**
     *  取得某一學年期未編班的的學生資料,並將同一個學生的所有選課資料組合起來成為一筆資料<====編班的部份
     */
    public Vector getQueryNonPlaForPla032m_OLD(Hashtable ht) throws Exception {
    	Vector result = new Vector();

        String sql =
        	"SELECT X.ORDER_TYPE,X.STNO,X.NAME,X.DETAIL_DATA, X.CRSNO,X.CENTER_ABBRNAME,X.CMPS_NAME,X.REMARK,X.CENTER_ABRCODE,X.CMPS_CODE, "+
        	"    MAX(X.STU_COUNT) OVER () AS MAX_STU_COUNT "+
        	"FROM "+
        	"( "+
        	// 一般面授
        	"    SELECT B.STNO,E.NAME,B.CRSNO||'-'||SUBSTR(C.CRS_NAME,1,4)||'-'||B.MASTER_CLASS_CODE AS DETAIL_DATA, " +
        	"        B.CRSNO,COUNT(1) OVER (PARTITION BY B.STNO) AS STU_COUNT,F.CENTER_ABBRNAME AS CENTER_ABBRNAME, " +
        	"        H.CMPS_NAME, G.REMARK, F.CENTER_ABRCODE, B.TUT_CMPS_CODE AS CMPS_CODE, '1' AS ORDER_TYPE "+
        	"    FROM REGT007 B  "+
        	"        JOIN COUT002 C ON C.CRSNO=B.CRSNO "+
        	"        JOIN STUT003 D ON D.STNO=B.STNO "+
        	"        JOIN STUT002 E ON E.IDNO=D.IDNO AND E.BIRTHDATE=D.BIRTHDATE "+
        	"        JOIN SYST002 F ON F.CENTER_CODE=D.CENTER_CODE "+
        	"        LEFT JOIN PLAT032 G ON B.AYEAR=G.AYEAR AND B.SMS=G.SMS AND G.CENTER_ABRCODE=F.CENTER_ABRCODE AND G.CMPS_CODE=TUT_CMPS_CODE AND B.STNO=G.STNO "+
        	"		 LEFT JOIN PLAT002 H ON B.AYEAR = H.AYEAR AND B.SMS = H.SMS AND B.TUT_CMPS_CODE = H.CMPS_CODE AND F.CENTER_ABRCODE = H.CENTER_ABRCODE  "+
        	"    WHERE "+
        	"            B.AYEAR='"+ht.get("AYEAR")+"' "+
        	"        AND B.SMS='"+ht.get("SMS")+"' "+
        	(!ht.get("CMPS_CODE").toString().equals("")?"AND B.TUT_CMPS_CODE='"+ht.get("CMPS_CODE").toString()+"' ":"")+ // 校區
        	(!ht.get("CENTER_ABRCODE").toString().equals("")?"AND F.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE").toString()+"' ":"")+ // 校區        	
        	"        AND B.UNQUAL_TAKE_MK = 'N'   "+
        	"        AND B.UNTAKECRS_MK = 'N'   "+
        	"        AND B.PAYMENT_STATUS > '1' "+
        	"        AND B.TUT_CMPS_CODE IS NOT NULL "+
        	"        AND B.MASTER_CLASS_CODE IS NULL "+
        	"        AND B.TUT_CLASS_CODE IS NULL "+
        	"        AND B.LAB_CLASS_CODE IS NULL "+
        	"        AND B.ASS_CLASS_CODE IS NULL "+
        	"        AND B.EXAM_CLASSM_CODE IS NULL "+
        	"    UNION "+
        	// 實習面授
        	"    SELECT B.STNO,E.NAME,B.CRSNO||'-'||SUBSTR(C.CRS_NAME,1,4)||'-'||B.LAB_CLASS_CODE AS DETAIL_DATA, " +
        	"        B.CRSNO,COUNT(1) OVER (PARTITION BY B.STNO) AS STU_COUNT,F.CENTER_ABBRNAME AS CENTER_ABBRNAME, " +
        	"        H.CMPS_NAME, G.REMARK, F.CENTER_ABRCODE, B.LAB_CMPS_CODE AS CMPS_CODE, '2' AS ORDER_TYPE "+
        	"    FROM REGT007 B  "+
        	"        JOIN COUT002 C ON C.CRSNO=B.CRSNO "+
        	"        JOIN STUT003 D ON D.STNO=B.STNO "+
        	"        JOIN STUT002 E ON E.IDNO=D.IDNO AND E.BIRTHDATE=D.BIRTHDATE "+
        	"        JOIN SYST002 F ON F.CENTER_CODE=D.CENTER_CODE "+
        	"        LEFT JOIN PLAT032 G ON B.AYEAR=G.AYEAR AND B.SMS=G.SMS AND G.CENTER_ABRCODE=F.CENTER_ABRCODE AND G.CMPS_CODE=LAB_CMPS_CODE AND B.STNO=G.STNO "+
        	"		 LEFT JOIN PLAT002 H ON B.AYEAR = H.AYEAR AND B.SMS = H.SMS AND B.LAB_CMPS_CODE = H.CMPS_CODE AND F.CENTER_ABRCODE = H.CENTER_ABRCODE  "+
        	"    WHERE "+
        	"            B.AYEAR='"+ht.get("AYEAR")+"' "+
        	"        AND B.SMS='"+ht.get("SMS")+"' "+
        	(!ht.get("CMPS_CODE").toString().equals("")?"AND B.LAB_CMPS_CODE='"+ht.get("CMPS_CODE").toString()+"' ":"")+ // 校區
        	(!ht.get("CENTER_ABRCODE").toString().equals("")?"AND F.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE").toString()+"' ":"")+ // 校區        	
        	"        AND B.UNQUAL_TAKE_MK = 'N'   "+
        	"        AND B.UNTAKECRS_MK = 'N'   "+
        	"        AND B.PAYMENT_STATUS > '1' "+
        	"        AND B.LAB_CMPS_CODE IS NOT NULL "+
        	"        AND B.MASTER_CLASS_CODE IS NULL "+
        	"        AND B.TUT_CLASS_CODE IS NULL "+
        	"        AND B.LAB_CLASS_CODE IS NULL "+
        	"        AND B.ASS_CLASS_CODE IS NULL "+
        	"        AND B.EXAM_CLASSM_CODE IS NULL "+        	
        	") X "+
        	"ORDER BY 2,1 ";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }
    
    // 一定會有兩筆資料,第一筆為一般面授,第二筆為實習面授校區
    private Vector getCmpsCondition(Hashtable ht, String centerAbrcode)throws Exception{
    	PLAT002DAO plat002 = new PLAT002DAO(dbmanager,conn);
    	plat002.setResultColumn("CMPS_CODE,CMPS_KIND");
    	plat002.setWhere(
    		"    AYEAR='"+ht.get("AYEAR")+"' "+
    		"AND SMS='"+ht.get("SMS")+"' "+
    		"AND CENTER_ABRCODE='"+centerAbrcode+"' "+
    		(ht.get("CMPS_CODE").toString().equals("")?"":"AND CMPS_CODE='"+ht.get("CMPS_CODE")+"' ")+
    		"ORDER BY CMPS_CODE "
    	);    	
    	DBResult rs = plat002.query();
    	
    	String tutCmpsCode = "";
    	String labCmpsCode = "";
    	while(rs.next()){
    		if(rs.getString("CMPS_KIND").equals("3"))
    			labCmpsCode += (labCmpsCode.equals("")?"":",")+rs.getString("CMPS_CODE");
    		else
    			tutCmpsCode += (tutCmpsCode.equals("")?"":",")+rs.getString("CMPS_CODE");
    	}
    	rs.close();
    	
    	Vector result = new Vector();
    	result.add(tutCmpsCode);
    	result.add(labCmpsCode);
    	
    	return result;
    }
    
    private Vector getCenterVt(Hashtable ht)throws Exception{
    	Vector result = new Vector();
    	
    	SYST002DAO syst002 = new SYST002DAO(dbmanager, conn);
    	syst002.setResultColumn("CENTER_ABRCODE");
    	syst002.setWhere("CENTER_CODE<>'00' "+(ht.get("CENTER_ABRCODE").toString().equals("")?"":"AND CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' ")+"ORDER BY CENTER_ABRCODE");    	
    	DBResult rs = syst002.query();
    	
    	while(rs.next()){
            result.add(rs.getString("CENTER_ABRCODE"));
    	}
    	rs.close();
    	
    	return result;
    }
    
    
    public Vector getReg025mPrint(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		try
		{
			if(sql.length() >0)
				sql.delete(0, sql.length());

			sql.append(
				"SELECT  R5.AYEAR, R5.SMS, SYS1.CODE_NAME AS SMS_NAME, R5.STNO AS STNO, "+
				"		 R7.CRSNO||C2.CRS_NAME AS CRS_NAME, S2.NAME, S3.CENTER_CODE, S02.CENTER_ABBRNAME, "+
				"		 TAKE_DATE||TAKE_TIME AS TAKE_DATE_TIME,R7.NOREFUND_DATE "+
				"FROM REGT005 R5 "+
				"JOIN REGT007 R7 ON R5.AYEAR =R7.AYEAR AND R5.SMS =R7.SMS AND R5.STNO =R7.STNO "+
				"JOIN STUT003 S3 ON R7.STNO = S3.STNO "+
				"JOIN STUT002 S2 ON S2.IDNO = S3.IDNO AND S2.BIRTHDATE = S3.BIRTHDATE "+
				"JOIN COUT002 C2 ON R7.CRSNO = C2.CRSNO "+
				"JOIN SYST002 S02 ON S02.CENTER_CODE = S3.CENTER_CODE "+
				"JOIN SYST001 SYS1 ON SYS1.KIND = 'SMS' AND SYS1.CODE = R5.SMS "+
				"WHERE 1=1 "+
            	"AND R5.TAKE_ABNDN = 'N' "+
            	"AND R7.UNQUAL_TAKE_MK = 'N' "+
            	"AND R7.UNTAKECRS_MK = 'Y' "+
            	"AND TRIM(R7.NOREFUND_DATE) IS NOT NULL "
			);
			
			if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")){
				sql.append("AND R5.AYEAR='"+ht.get("AYEAR").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("SMS")).equals("")){
				sql.append("AND R5.SMS='"+ht.get("SMS").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("STNO")).equals("")){
				sql.append("AND R5.STNO = '"+ht.get("STNO").toString()+"' ");
			}
            if(!Utility.nullToSpace(ht.get("BIRTHDATE")).equals("")){
				sql.append("AND S3.BIRTHDATE = '"+ht.get("BIRTHDATE").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("IDNO")).equals("")){
				sql.append("AND S3.IDNO = '"+ht.get("IDNO").toString()+"' ");
			}
			if(!Utility.nullToSpace(ht.get("CENTER_CODE")).equals("")){
				sql.append("AND S3.CENTER_CODE = '"+ht.get("CENTER_CODE").toString()+"' ");
			}
			sql.append("ORDER BY S3.CENTER_CODE, R5.STNO, R7.CRSNO ");

			DBResult rs = null;
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++)
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				result.add(rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}
    
    public Hashtable getAVG_GRADEForCqr102r_01v1(Hashtable ht) throws Exception {
    	Hashtable result = new Hashtable();

		if(sql.length() >0)
			sql.delete(0, sql.length());

		sql.append(
			"SELECT B.CENTER_ABRCODE,B.TCH_IDNO,A.CRSNO,A.ASS_CLASS_CODE,COUNT(DISTINCT A.STNO) AS CLASS_COUNT,SUM(D.CRSNO_SMSGPA) AS TOTAL_GRADE,COUNT(D.CRSNO_SMSGPA) AS AVG_GRADE "+
			"FROM REGT007 A "+
			"JOIN PLAT012 B ON A.AYEAR = B.AYEAR AND A.SMS = B.SMS AND A.CRSNO = B.CRSNO AND A.ASS_CLASS_CODE = B.CLASS_CODE AND B.TCH_IDNO IS NOT NULL "+ 
			"LEFT JOIN SCDW004 C ON C.AYEAR = A.AYEAR AND C.SMS = A.SMS AND C.CRSNO = A.CRSNO AND C.ASGN_CLASS_CODE = A.ASS_CLASS_CODE AND A.STNO = C.STNO "+
			"LEFT JOIN SCDT004 D ON D.AYEAR = C.AYEAR AND D.SMS = C.SMS AND D.CRSNO = C.CRSNO AND C.TUT_CLASS_CODE = D.TUT_CLASS_CODE AND C.STNO = D.STNO AND D.CRSNO_SMSGPA > 0 "+
			"WHERE 1=1 "+
			"AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' "+ 
			"AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' "+
			"AND A.UNQUAL_TAKE_MK = 'N' "+
			"AND A.UNTAKECRS_MK = 'N' "+
			"AND A.PAYMENT_STATUS <> '1' "
		);
		
		if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
    		sql.append("AND A.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
    	}
		sql.append("GROUP BY B.CENTER_ABRCODE,B.TCH_IDNO,A.CRSNO,A.ASS_CLASS_CODE ");
		sql.append("ORDER BY B.CENTER_ABRCODE,B.TCH_IDNO,A.CRSNO,A.ASS_CLASS_CODE");
		
			System.out.println(sql.toString());
		try
		{
			DBResult rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());
			
			Hashtable rowHt = null;
			while (rs.next())
			{
				rowHt = new Hashtable();
				rowHt.put("CLASS_COUNT", rs.getString("CLASS_COUNT"));
				rowHt.put("TOTAL_GRADE", rs.getString("TOTAL_GRADE"));
				rowHt.put("AVG_GRADE", rs.getString("AVG_GRADE"));
				result.put(rs.getString("CENTER_ABRCODE")+"_"+rs.getString("TCH_IDNO")+"_"+rs.getString("CRSNO")+"_"+rs.getString("ASS_CLASS_CODE"), rowHt);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}
    
    
    public Vector getDataForScd038m_01v1(Hashtable ht) throws Exception {
    	Vector result = new Vector();

		if(sql.length() >0)
			sql.delete(0, sql.length());

		sql.append(
			"SELECT UNIQUE A.AYEAR,A.SMS,B.CENTER_ABBRNAME,C.CMPS_NAME,A.CRSNO,D.CRS_NAME "+
			"FROM REGT007 A "+
			"JOIN COUT002 D ON D.CRSNO = A.CRSNO "+
			"JOIN SYST002 B ON SUBSTR(NVL(A.TUT_CLASS_CODE,A.LAB_CLASS_CODE),1,1) = B.CENTER_ABRCODE "+
			"LEFT JOIN PLAT002 C ON A.AYEAR = C.AYEAR AND A.SMS = C.SMS AND B.CENTER_ABRCODE = C.CENTER_ABRCODE AND SUBSTR(NVL(A.TUT_CLASS_CODE,A.LAB_CLASS_CODE),2,1)  = C.CMPS_CODE "+
			"WHERE 1=1 "+
			"AND A.UNQUAL_TAKE_MK = 'N' "+
			"AND A.UNTAKECRS_MK = 'N' "+
			"AND A.PAYMENT_STATUS !='1' "+
			"AND NVL(A.TUT_CLASS_CODE,A.LAB_CLASS_CODE) IS NOT NULL "+
			"AND SUBSTR(A.TUT_CLASS_CODE,3,2) NOT IN ('AA','Z0') "+
			"AND A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' "+
			"AND A.SMS = '"+Utility.nullToSpace(ht.get("SMS"))+"' "+
			"AND A.CRSNO = '"+Utility.nullToSpace(ht.get("CRSNO"))+"' "
		);
		
		if(!Utility.nullToSpace(ht.get("CENTER_CODE")).equals("")){
			sql.append("AND B.CENTER_CODE='"+ht.get("CENTER_CODE").toString()+"' ");
		}
			
		DBResult rs = null;
        try {
        	if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
	}
    
    public Vector getPla113rToExport(Hashtable ht) throws Exception {
    	Vector result = new Vector();

    	sql.append(
    			"SELECT C.CENTER_NAME, D.CMPS_NAME, A.CRSNO, a.TUT_CMPS_CODE, E.CRS_NAME, A.STNO, F.NAME, F.MOBILE, F.CRRSADDR_ZIP, " +
    			"	F.CRRSADDR, F.EMAIL, f.AREACODE_OFFICE, f.TEL_OFFICE, f.TEL_OFFICE_EXT, f.AREACODE_HOME,f.TEL_HOME \n"+ 
    			"FROM REGT007 A  \n"+
    			"JOIN STUT003 B ON A.STNO=B.STNO  \n"+
    			"JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE AND C.CENTER_ABRCODE = '"+ht.get("CENTER_ABRCODE")+"'  \n"+
    			"JOIN PLAT002 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND C.CENTER_ABRCODE=D.CENTER_ABRCODE AND A.TUT_CMPS_CODE=D.CMPS_CODE \n"+ 
    			"JOIN COUT002 E ON A.CRSNO=E.CRSNO  \n"+
    			"JOIN STUT002 F ON B.IDNO=F.IDNO AND B.BIRTHDATE=F.BIRTHDATE \n"+ 
    			"WHERE \n"+
    			"    A.AYEAR = '"+ht.get("AYEAR")+"'  \n"+
    			"AND A.SMS = '"+ht.get("SMS")+"'  \n"+
    			"AND A.UNQUAL_TAKE_MK='N'  \n"+
    			"AND A.UNTAKECRS_MK='N'  \n"+
    			(!Utility.nullToSpace(ht.get("CMPS_CODE")).equals("")?"AND A.TUT_CMPS_CODE = '"+Utility.nullToSpace(ht.get("CMPS_CODE"))+"'  \n":"")+
    			(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")?"AND A.PAYMENT_STATUS > '1'  \n":"")+
    			"GROUP BY C.CENTER_NAME, D.CMPS_NAME, A.CRSNO, a.TUT_CMPS_CODE, E.CRS_NAME, A.STNO, F.NAME, F.MOBILE, F.CRRSADDR_ZIP, F.CRRSADDR, F.EMAIL, \n"+
    			"    f.areacode_office, f.tel_office, f.tel_office_ext, f.areacode_home, f.tel_home \n"+
    			"HAVING COUNT(1) < "+ht.get("NUMBER")+" \n"+
				"ORDER BY  C.CENTER_NAME,a.TUT_CMPS_CODE, a.CRSNO, a.stno  \n");

		DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
	}
    
 // 清空學生面授班級資訊
    public void clearRegt007ClassCode(Hashtable ht) throws Exception {
    	String updateRegt007 = 
        	"UPDATE REGT007 \n"+
        	"SET TUT_CLASS_CODE='' , MASTER_CLASS_CODE='' ,ASS_CLASS_CODE='' ,EXAM_CLASSM_CODE='' ,TUT_SEAT='' ,EXAM_SEAT='' \n"+
        	"WHERE AYEAR||SMS||CRSNO||STNO IN \n" +
        	"(	\n"+
        	"	SELECT A.AYEAR||A.SMS||A.CRSNO||A.STNO	\n"+
			"	FROM REGT007 A	\n"+
			"	JOIN STUT003 B ON A.STNO=B.STNO	\n"+
			"	JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE AND C.CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"'	\n"+
			"	WHERE	\n"+
			"	    A.AYEAR='"+ht.get("AYEAR")+"'	\n"+
			"	AND A.SMS='"+ht.get("SMS")+"'	\n"+
			"	AND A.TUT_CMPS_CODE='"+ht.get("CMPS_CODE")+"'	\n"+
			(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")?"AND A.PAYMENT_STATUS>'1' \n":"")+
			"	AND A.UNQUAL_TAKE_MK = 'N' "+
			"	AND A.UNTAKECRS_MK = 'N' "+
			// 僅清空一般面授
			"	AND EXISTS (SELECT 1 FROM PLAT009 D WHERE A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND A.CRSNO=D.CRSNO AND C.CENTER_ABRCODE=D.CENTER_ABRCODE AND A.TUT_CMPS_CODE=D.CMPS_CODE AND D.CLASS_KIND='1')	\n"+
			")	";
    	
        REGT007DAO regt007 = new REGT007DAO(dbmanager, conn);
        regt007.execute(updateRegt007);
	}
    
    // 取得學生資料(按傷殘別,老人,選課數加權,學號來排序)
    public Vector getStuDataForPla(Hashtable ht, Hashtable specialStuCrsno) throws Exception {
    	Vector result = new Vector();
    	
		DBResult rs = null;
        try {
        	//STEP.0 如CODE=2 (正排查詢選擇顯示科目中文時,要一起顯示該生所選的科目的所有班級排序)
        	Hashtable crsnoSeqHt = new Hashtable();
        	if(Utility.nullToSpace(ht.get("CODE")).equals("2")){
        		PLAT012GATEWAY plat012 = new PLAT012GATEWAY(dbmanager, conn);
        		crsnoSeqHt = plat012.qcrsno_sectionHt(ht);        		
        	} 
        	
        	// STEP.1   
        	// a.取得學生選課數優先排的順序(用來決定要排先排哪個學生)
        	// b.取得編班參數,開班數比率和時段數比率(用來決定某個學生哪一科要先排的參數)
        	PLAT002DAO plat002 = new PLAT002DAO(dbmanager, conn);
        	plat002.setResultColumn("CRSNO_NUM_ORD,CLASS_NUM_WEIGHT,SEGMENT_CODE_NUM_WEIGHT");
        	plat002.setWhere("AYEAR='"+ht.get("AYEAR")+"' AND SMS='"+ht.get("SMS")+"' AND CENTER_ABRCODE='"+ht.get("CENTER_ABRCODE")+"' AND CMPS_CODE='"+ht.get("CMPS_CODE")+"' ");
        	rs = plat002.query();
        	
        	String crsnoCountSeq = "\n\t CASE WHEN 1=2 THEN 'Z' \n";  // 按選課數排序的SQL
        	int classNumWeight = 0; // 開班數比重
        	int segmentCodeNumWeight = 0; // 時段數比重
        	if(rs.next()){
        		String[] crsnoNumOrd = Utility.split(rs.getString("CRSNO_NUM_ORD"), ",");
        		
        		for(int i=0; i<crsnoNumOrd.length; i++){
        			if(crsnoNumOrd.equals(""))
        				continue;
        			
        			crsnoCountSeq+="\t WHEN TO_CHAR(COUNT(1))='"+crsnoNumOrd[i]+"' THEN '"+i+"' \n";
        		}
        		
        		classNumWeight = rs.getInt("CLASS_NUM_WEIGHT");
        		segmentCodeNumWeight = rs.getInt("SEGMENT_CODE_NUM_WEIGHT");
        	}
        	crsnoCountSeq+="\t ELSE 'Z' END \n";
        	rs.close();
        	
        	
        	// STEP.2	按參數設定檔取出學生資料且按傷殘別,老人,選課數加權,學號來排序
        	String sql =
        		" \n"+
        		"SELECT IDNO, B.AYEAR||B.SMS||B.CENTER_ABRCODE||B.CMPS_CODE||A.CRSNO AS G_KEY, B.AYEAR, B.SMS, B.CENTER_ABRCODE, \n" +
        		"	B.CMPS_CODE, B.BIRTHDATE, B.STNO, A.CRSNO, E.NAME AS CSTNO, F.CRS_NAME AS CCRSNO , A.TUT_CLASS_CODE AS CLASS_CODE, \n"+
        		"	CASE TO_CHAR(LENGTH(A.TUT_CLASS_CODE)) WHEN '6' THEN TO_CHAR(SUBSTR(A.TUT_CLASS_CODE,3,1)) ELSE 'X' END AS TWEEK," +
        		"	CASE TO_CHAR(LENGTH(A.TUT_CLASS_CODE)) WHEN '6' THEN TO_CHAR(SUBSTR(A.TUT_CLASS_CODE,4,1)) ELSE 'X' END AS TSECTION," +
        		"	-- 如為老人/傷殘則顯示該文字   TRUNC---無條件捨去小數點 \n"+
                "	(CASE WHEN TRUNC((TO_DATE(G.ENROLL_BASEDATE,'YYYYMMDD')-TO_DATE(B.BIRTHDATE,'YYYYMMDD'))/365) >=65 THEN '老人' ELSE '' END)||DECODE (NVL (d.stno, 'X'), 'X', '', SUBSTR(H.CODE_NAME,1,1)||'殘') AS SPECIAL_MK,  \n"+
                "	-- 學生編班順序,傷殘(-2),老人(-1),期他的人則是按設定的選課科目數(B.ORD)來排序		\n"+
                "	DECODE (NVL (D.STNO, 'X'), 'X', (CASE WHEN TRUNC((TO_DATE(G.ENROLL_BASEDATE,'YYYYMMDD')-TO_DATE(B.BIRTHDATE,'YYYYMMDD'))/365)>=65 THEN '-1' ELSE to_char(B.ORD) END),'-2') AS ORD,   \n"+
                "	-- CODE=1 表示顯示科目代碼	CODE=2顯示科目名稱	NUM1:科目名稱顯示多少字		\n"+
                "	(CASE WHEN '"+Utility.nullToSpace(ht.get("CODE"))+"'='1' THEN TO_CHAR(A.CRSNO) WHEN '"+Utility.nullToSpace(ht.get("CODE"))+"'='2' THEN TO_CHAR(SUBSTR(F.CRS_NAME,0,"+Utility.checkNull(ht.get("NUM1"),"4")+")) ELSE TO_CHAR(A.CRSNO) END) AS SHOW_CRSNO, \n"+
                "	-- 按科目比重排序,  \n"+
                "  	I.OPEN_CLASS_NUM*"+classNumWeight+"+I.OPEN_SECTION_NUM*"+segmentCodeNumWeight+" AS CRSNO_WEIGHT \n"+
                "FROM REGT007 A  \n" +
                "-- 一個學生一筆資料,取得學生的選課數排序(ORD),join這個單純為了排序用,為了之後讓哪個學生優先排 \n"+
                "JOIN  \n" +
                "(  \n" +
                "	SELECT A.AYEAR,A.SMS,C.CENTER_ABRCODE,A.TUT_CMPS_CODE AS CMPS_CODE,A.STNO,B.IDNO,B.BIRTHDATE ,"+crsnoCountSeq+"  AS ORD  \n" +
                "	FROM REGT007 A   \n" +
                "	JOIN STUT003 B ON A.STNO=B.STNO  \n" +
                "	JOIN SYST002 C ON B.CENTER_CODE=C.CENTER_CODE  \n" +
                "	WHERE   \n"+
                "	A.UNQUAL_TAKE_MK = 'N'  \n"+
                "	AND A.UNTAKECRS_MK = 'N'  \n"+
                	// 是否含未繳費
                	(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")?"AND A.PAYMENT_STATUS <> '1'  \n":"")+
                "	AND A.AYEAR='" + Utility.nullToSpace(ht.get("AYEAR")) +"'  \n"+
                "	AND A.SMS='" + Utility.nullToSpace(ht.get("SMS")) +"'  \n"+
                "	AND C.CENTER_ABRCODE='" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) +"'  \n"+
                "	AND A.TUT_CMPS_CODE='" + Utility.nullToSpace(ht.get("CMPS_CODE")) +"'  \n"+
                "   AND EXISTS (SELECT 1 FROM COUT001 A1 WHERE A1.EST_RESULT_MK='Y' AND A.AYEAR=A1.AYEAR AND A.SMS=A1.SMS AND A.CRSNO=A1.CRSNO)  \n"+
                "   AND EXISTS (SELECT 1 FROM PLAT009 A1 WHERE A1.CLASS_KIND='1' AND A.AYEAR=A1.AYEAR AND A.SMS=A1.SMS AND A.CRSNO=A1.CRSNO AND A1.CENTER_ABRCODE = C.CENTER_ABRCODE  AND A.TUT_CMPS_CODE=A1.CMPS_CODE)  \n"+
                "   GROUP BY A.AYEAR,A.SMS,C.CENTER_ABRCODE,A.TUT_CMPS_CODE,A.STNO,B.IDNO,B.BIRTHDATE  \n"+
                ") B  ON A.AYEAR=B.AYEAR AND A.SMS=B.SMS AND A.STNO=B.STNO  \n"+
                "JOIN STUT002 E ON B.BIRTHDATE=E.BIRTHDATE AND B.IDNO=E.IDNO    \n"+
                "JOIN COUT002 F ON A.CRSNO=F.CRSNO  \n"+
                "JOIN SYST005 G ON A.AYEAR=G.AYEAR AND A.SMS=G.SMS   \n"+
                "LEFT JOIN REGT004 D ON A.AYEAR=D.AYEAR AND A.SMS=D.SMS AND A.STNO=D.STNO AND D.REDUCE_TYPE IN ('01','02','03') AND D.AUDIT_STATUS='1' \n"+
                "LEFT JOIN SYST001 H ON H.KIND='REDUCE_TYPE' AND D.REDUCE_TYPE=H.CODE \n"+
                "-- 用來判斷要先排哪一科(1.上面的sub query 是先決定排哪個學生   2.下面這段sub query是決定某個學生要先排哪一個科目,公式是:開班數xPLAT002的開班數比率+時段數xPLAT002的時段數比率,值越大表示比較好排,因此該科比較晚排) \n"+
                "JOIN \n"+
                "( \n"+
	            "    select unique count(1) OVER(PARTITION BY crsno) as OPEN_CLASS_NUM,  \n"+
		        "    	count(distinct segment_code||section_code) OVER(PARTITION BY crsno) as OPEN_SECTION_NUM, crsno \n"+
		        "    from plat011 a \n"+
		        "    where \n"+
		        "        a.ayear='" + Utility.nullToSpace(ht.get("AYEAR")) + "' \n"+
		        "    and a.sms='" + Utility.nullToSpace(ht.get("SMS")) + "' \n"+
		        "    and a.center_abrcode='" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' \n"+
		        "    and a.cmps_code='" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' \n"+                
                ") I ON A.CRSNO=I.CRSNO \n"+
                "WHERE  \n"+
                "    A.UNQUAL_TAKE_MK = 'N'  \n"+
                "AND A.UNTAKECRS_MK = 'N'  \n"+
                // 是否含未繳費
                (Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")?"AND A.PAYMENT_STATUS <> '1'  \n":"")+
                "AND EXISTS (SELECT 1 FROM COUT001 A1 WHERE A1.EST_RESULT_MK='Y' AND A.AYEAR = A1.AYEAR AND A.SMS=A1.SMS AND A.CRSNO=A1.CRSNO)  \n"+
                "AND EXISTS (SELECT 1 FROM PLAT009 A1 WHERE A1.CLASS_KIND='1' AND A.AYEAR=A1.AYEAR AND A.SMS=A1.SMS AND A.CRSNO=A1.CRSNO AND A1.CENTER_ABRCODE='" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' AND A.TUT_CMPS_CODE=A1.CMPS_CODE)  \n"+
                "AND A.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "'  \n"+
                "AND A.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "'  \n"+
                "AND B.CENTER_ABRCODE = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "'  \n"+
                "AND A.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "'  \n"+
                "AND B.CMPS_CODE = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "'  \n"+
                (Utility.nullToSpace(ht.get("CRSNO")).equals("")?"":"AND EXISTS (SELECT 1 FROM REGT007 A1 WEHRE A.AYEAR=A1.AYEAR AND A.SMS=A1.SMS AND A.STNO=A1.STNO AND A1.CRSNO='"+ht.get("CRSNO")+"' AND A1.UNQUAL_TAKE_MK='N' AND A1.UNTAKECRS_MK='N' "+(Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")?"AND A1.PAYMENT_STATUS > '1' ":"")+ ") \n")+                
                "ORDER BY ORD,A.STNO,CRSNO_WEIGHT,CRSNO \n";
        	
        	System.out.println(sql);
        	rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql);
			
			String specialStuCrsnoStr = "'zxa'"; // 特殊生選課科目代碼(預設值..單純讓sql查無資料)
			Vector tmp = new Vector();
            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
                
                // 如CODE=2 (正排查詢選擇顯示科目中文時,要一起顯示該生所選的科目的所有班級排序)
                if(Utility.nullToSpace(ht.get("CODE")).equals("2")&&crsnoSeqHt.containsKey(rs.getString("G_KEY")))
                	rowHt.put("SHOW_CRSNO", rs.getString("SHOW_CRSNO")+crsnoSeqHt.get(rs.getString("G_KEY")));
                
                // 表示特殊生,-2:傷殘(第一優先排)		-1:老人(第二優先排)
                if(rs.getInt("ORD")<0)
                	specialStuCrsnoStr+=",'"+rs.getString("CRSNO")+"'";
                
                // 正排會有值,預排不會有值
                rowHt.put("CLASS_ROOM_ARRANGE", Utility.nullToSpace(ht.get("CLASS_ROOM_ARRANGE")));            
                	
                tmp.add(rowHt);
            }
            specialStuCrsno.put("SPECIAL_STU_CRSNO", specialStuCrsnoStr);
            
            // tmp為一筆資料表示:一個學生一科   下面的步驟是將資料整理成,一個學生一筆資料,如有多個科目時,則欄位則用逗號隔開
            Vector pk = new Vector();
            pk.add("STNO");
            
            // 合併欄位
            Vector addColumn = new Vector();
            addColumn.add("G_KEY");
            addColumn.add("CRSNO");
            addColumn.add("CCRSNO");
            addColumn.add("CLASS_CODE");
            addColumn.add("TWEEK");
            addColumn.add("TSECTION");
            addColumn.add("SHOW_CRSNO");
            
            Vector betweenAddColumn = new Vector();            
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            betweenAddColumn.add(",");
            
            // 將資料整理好給result
            UtilityX.combinCompareNextTheSameData(pk, addColumn, betweenAddColumn, tmp, result);            
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
	}
    
    public Hashtable getDataForReg179r(Hashtable ht) throws Exception {
    	if(ht == null) {
            ht = new Hashtable();
        }
    	Hashtable rowHt = new Hashtable();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        String condition = " ('"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"','2'), "+
        				   " ('"+Utility.nullToSpace(ht.get("AYEAR"))+"','1'),"+
        				   " ('"+Utility.nullToSpace(ht.get("AYEAR"))+"','3') ";        
        
        sql.append(
			//"SELECT TO_CHAR('1') AS SEQ, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.STNO)) AS CNT \n"+
        	"SELECT TO_CHAR('1') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT( A.STNO)) AS CNT \n"+//維立  更改同一學生不同學期人科次
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE "+
			"WHERE 1=1  \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('2') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(SUM(E.TOTAL_FEE))  AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"JOIN REGT010 D ON D.AYEAR = A.AYEAR AND D.SMS = A.SMS AND D.STNO = A.STNO \n"+
			"              AND SUBSTR(D.WRITEOFF_NO,7) = (SELECT MAX(SUBSTR(R10.WRITEOFF_NO,7)) \n"+
			"                                             FROM REGT010 R10 \n"+
			"                                             WHERE R10.AYEAR = D.AYEAR \n"+
			"                                             AND   R10.SMS = D.SMS \n"+
			"                                             AND   R10.STNO = D.STNO \n"+
			"                                             AND   R10.ABNDN_ORDER = 'N') \n"+
			"JOIN REGT011 E ON E.WRITEOFF_NO = D.WRITEOFF_NO AND E.CRSNO = A.CRSNO \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('3') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '1' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('4') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '2' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('5') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+ 
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '6' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME ,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('6') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT G.IDNO)) AS CNT\n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+ 
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"JOIN TRAT006 F ON F.CRSNO = A.CRSNO AND F.DUTY_CODE = '05' AND F.AYEAR||F.SMS = A.AYEAR || A.SMS \n"+
			"JOIN TRAT001 G ON G.IDNO = F.IDNO AND G.NOU_VOCATION_TYPE = '1' \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"ORDER BY 1,2 \n"
		);
		
		DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

            while (rs.next()) {
                rowHt.put(rs.getString("SEQ")+"_"+rs.getString("AYEAR")+rs.getString("SMS")+"_"+rs.getString("FACULTY_CODE"), rs.getString("CNT"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return rowHt;
	}
    
    public Hashtable getDataForReg180r(Hashtable ht) throws Exception {
    	if(ht == null) {
            ht = new Hashtable();
        }
    	Hashtable rowHt = new Hashtable();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        String condition = " ('"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"','2'), "+
        				   " ('"+Utility.nullToSpace(ht.get("AYEAR"))+"','1'),"+
        				   " ('"+Utility.nullToSpace(ht.get("AYEAR"))+"','3') ";        
        
        sql.append(
			//"SELECT TO_CHAR('1') AS SEQ, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.STNO)) AS CNT \n"+
        	"SELECT TO_CHAR('1') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT( A.STNO)) AS CNT \n"+//維立  更改同一學生不同學期人科次
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE "+
			"JOIN REGT010 D ON D.AYEAR = A.AYEAR AND D.SMS = A.SMS AND D.STNO = A.STNO \n"+
			"              AND SUBSTR(D.WRITEOFF_NO,7) = (SELECT MAX(SUBSTR(R10.WRITEOFF_NO,7)) \n"+
			"                                             FROM REGT010 R10 \n"+
			"                                             WHERE R10.AYEAR = D.AYEAR \n"+
			"                                             AND   R10.SMS = D.SMS \n"+
			"                                             AND   R10.STNO = D.STNO \n"+
			"                                             AND   R10.ABNDN_ORDER = 'N') \n"+
			"JOIN REGT011 E ON E.WRITEOFF_NO = D.WRITEOFF_NO AND E.CRSNO = A.CRSNO \n"+
			"WHERE 1=1  \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"AND E.REDUCE_PRICE <> '0' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('2') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(SUM(E.REDUCE_PRICE))  AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"JOIN REGT010 D ON D.AYEAR = A.AYEAR AND D.SMS = A.SMS AND D.STNO = A.STNO \n"+
			"              AND SUBSTR(D.WRITEOFF_NO,7) = (SELECT MAX(SUBSTR(R10.WRITEOFF_NO,7)) \n"+
			"                                             FROM REGT010 R10 \n"+
			"                                             WHERE R10.AYEAR = D.AYEAR \n"+
			"                                             AND   R10.SMS = D.SMS \n"+
			"                                             AND   R10.STNO = D.STNO \n"+
			"                                             AND   R10.ABNDN_ORDER = 'N') \n"+
			"JOIN REGT011 E ON E.WRITEOFF_NO = D.WRITEOFF_NO AND E.CRSNO = A.CRSNO \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('3') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '1' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('4') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '2' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('5') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT A.CRSNO)) AS CNT \n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO AND B.NEW_REWORK = '6' \n"+
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"UNION ALL \n"+
			"SELECT TO_CHAR('6') AS SEQ,A.AYEAR,A.SMS, C.FACULTY_CODE,C.FACULTY_NAME,TO_CHAR(COUNT(DISTINCT G.IDNO)) AS CNT\n"+
			"FROM REGT007 A \n"+
			"JOIN COUT001 B ON B.AYEAR = A.AYEAR AND B.SMS = A.SMS AND B.CRSNO = A.CRSNO \n"+ 
			"JOIN SYST003 C ON C.FACULTY_CODE = B.PLAN_FACULTY_CODE \n"+
			"JOIN TRAT006 F ON F.CRSNO = A.CRSNO AND F.DUTY_CODE = '05' AND F.AYEAR||F.SMS = A.AYEAR || A.SMS \n"+
			"JOIN TRAT001 G ON G.IDNO = F.IDNO AND G.NOU_VOCATION_TYPE = '1' \n"+
			"where 1=1 \n"+
			//"AND ((A.AYEAR = '"+Utility.fillZero(String.valueOf(Integer.parseInt(Utility.nullToSpace(ht.get("AYEAR")))-1),3)+"' AND A.SMS = '2') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '3') OR (A.AYEAR = '"+Utility.nullToSpace(ht.get("AYEAR"))+"' AND A.SMS = '1')) \n"+
			"AND (A.AYEAR,A.SMS) IN ("+condition+")   \n"+
			"AND A.UNQUAL_TAKE_MK = 'N' \n"+
			"AND A.UNTAKECRS_MK = 'N' \n"+
			"AND A.PAYMENT_STATUS <> '1' \n"+
			"GROUP BY C.FACULTY_CODE,C.FACULTY_NAME,A.AYEAR,A.SMS \n"+
			"ORDER BY 1,2 \n"
		);
		
		DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(sql.toString());

            while (rs.next()) {
            	rowHt.put(rs.getString("SEQ")+"_"+rs.getString("AYEAR")+rs.getString("SMS")+"_"+rs.getString("FACULTY_CODE"), rs.getString("CNT"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return rowHt;
	}
    
    // 取得遠距人數
    public String classType4StuNum(Hashtable ht, String classCode)throws Exception{
    	String stuNum = "";
    	
    	DBResult rs = null;
    	try{
	    	String getStuNum = 	"select count(1) as STU_NUM "+
			"from regt007 a, stut003 b, syst002 c "+
			"where "+
			"a.ayear='"+ht.get("AYEAR")+"' "+
			"and a.sms='"+ht.get("SMS")+"' "+
			"and a.crsno='"+ht.get("CRSNO").toString()+"' "+
			"and a.tut_class_code='"+classCode+"' "+
			"and a.UNQUAL_TAKE_MK='N' "+
			"and a.UNTAKECRS_MK='N' "+
			"and a.PAYMENT_STATUS>'1' "+
			"and c.center_abrcode='"+ht.get("CENTER_ABRCODE").toString()+"' "+								
			"and b.stno=a.stno "+
			"and c.center_code=b.center_code ";
	    	
			rs = dbmanager.getSimpleResultSet(conn);
			rs.open();
			rs.executeQuery(getStuNum);
			if(rs.next())
				stuNum = rs.getString("STU_NUM");
			rs.close();
    	}finally{
    		if(rs!=null)
    			rs.close();
    	}
    	
    	return stuNum;
    }
    
    public Vector getCqrDataForCqr012m(Hashtable ht) throws Exception {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        
        sql.append(
        	"select a.AYEAR, a.SMS, a.STNO, a.CRSNO, a.ASS_CLASS_CODE, b.CRS_NAME, c.OPEN_DATE, c.COLSE_DATE, d.QR_CODE, d.QSTNNR_NAME, "+
        	"(select count(1) from cqrt006 d where c.AYEAR=d.AYEAR and c.SMS=d.SMS and c.CRSNO=d.CRSNO and c.QR_CODE=d.QR_CODE and d.STNO='"+ht.get("STNO")+"') as IS_WRITE "+
        	"from regt007 a "+
        	"join cout002 b on a.CRSNO=b.CRSNO "+
        	"join cqrt005 c on a.AYEAR=c.AYEAR and a.SMS=c.SMS and a.CRSNO=c.CRSNO and to_char(sysdate,'yyyymmdd') between c.OPEN_DATE and c.COLSE_DATE "+
        	"join cqrt001 d on c.QR_CODE=d.QR_CODE "+
        	"where 1=1"+
        	"and a.AYEAR='"+ht.get("AYEAR")+"' "+
        	"and a.SMS='"+ht.get("SMS")+"' "+
        	"and a.STNO='"+ht.get("STNO")+"' "+
        	"and a.UNQUAL_TAKE_MK='N' "+
        	"and a.UNTAKECRS_MK='N' "+
        	"and a.PAYMENT_STATUS>'1' "+
        	"and not exists(select 1 from cqrt006 cqr06 where cqr06.AYEAR = a.AYEAR and cqr06.sms = a.sms and cqr06.QR_CODE = d.QR_CODE and cqr06.stno = a.STNO and a.CRSNO = cqr06.CRSNO) "
		);

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
    
    
    public Vector getrReg036mQuery(Hashtable ht) throws Exception {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        
        sql.append(
        		"SELECT "+
        		"M.AYEAR,M.SMS,M.STNO,M.CRSNO,M.TUT_CMPS_CODE,D.WRITEOFF_NO,"+
        		"B.NAME,C.CRS_NAME,C.CRD,"+
        		"TO_NUMBER(M.AYEAR)||S1.CODE_NAME AS AYEARSMS_NAME "+
        		"FROM REGT007 M "+
        		"JOIN STUT003 A ON M.STNO = A.STNO "+
        		"JOIN STUT002 B ON A.IDNO = B.IDNO AND A.BIRTHDATE = B.BIRTHDATE "+
        		"JOIN COUT002 C ON M.CRSNO = C.CRSNO "+
        		"JOIN REGT010 D ON M.AYEAR = D.AYEAR AND M.SMS = D.SMS AND M.STNO = D.STNO "+   
        		"AND SUBSTR(D.WRITEOFF_NO,7)=  (SELECT MAX(SUBSTR(WRITEOFF_NO,7)) WRITEOFF_NO FROM REGT010 WHERE 1 = 1 AND AYEAR=D.AYEAR AND SMS=D.SMS AND STNO = A.STNO) "+
        		"JOIN SYST001 S1 ON S1.KIND = 'SMS' AND S1.CODE = M.SMS "+
        		"WHERE 1=1 "+
        		"AND TRIM(M.LAB_CMPS_CODE) IS NULL "+
        		"AND TRIM(M.MASTER_CLASS_CODE) IS NULL "+
        		"AND TRIM(M.TUT_CLASS_CODE) IS NULL "+
        		"AND TRIM(M.LAB_CLASS_CODE) IS NULL "+
        		"AND TRIM(M.EXAM_CLASSM_CODE) IS NULL "+
        		"AND TRIM(M.ASS_CLASS_CODE) IS NULL "+
        		""
		);
        
        if(!Utility.nullToSpace(ht.get("STNO")).equals("")) {
            sql.append("AND M.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
        }
        
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND M.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND M.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        
        if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
            sql.append("AND M.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
        }
        
        if(!Utility.nullToSpace(ht.get("TUT_CMPS_CODE")).equals("")) {
            sql.append("AND M.TUT_CMPS_CODE = '" + Utility.nullToSpace(ht.get("TUT_CMPS_CODE")) + "' ");
        }
        
        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }
    
    /**
     *  取得某一學年期某個學生的選課資料
     */
    public Hashtable getReg135rRegData(Hashtable ht) throws Exception {
    	Hashtable rowHt = new Hashtable();

        if(sql.length() > 0)
            sql.delete(0, sql.length());

        String sql =
        	"SELECT "+
        	"SUM(B.CRD) AS CRD, "+
        	"SUM( NVL(D.CRD_FEE,'0') * NVL(B.CRD,'0')  ) AS CRD_FEE, "+
        	"SUM( NVL(D.MISC_FEE,'0') * NVL(B.CRD,'0')  ) AS MISC_FEE, "+
        	"SUM( NVL(C.LAB_FEE,'0')) AS LAB_FEE, "+
        	"SUM( NVL(D.CRD_FEE,'0') * NVL(B.CRD,'0')  ) + SUM( NVL(D.MISC_FEE,'0') * NVL(B.CRD,'0')  ) +SUM( NVL(C.LAB_FEE,'0'))  AS TOTAL_FEE "+
        	"FROM REGT007 A "+
        	"JOIN COUT002 B ON B.CRSNO = A.CRSNO "+
        	"LEFT JOIN REGT002 C ON A.AYEAR = C.AYEAR AND A.SMS = C.SMS AND A.CRSNO = C.CRSNO  "+
        	"LEFT JOIN REGT001 D ON A.AYEAR = D.AYEAR AND A.SMS = D.SMS AND A.ASYS = D.ASYS "+
        	"WHERE A.AYEAR = '"+Utility.dbStr(Utility.checkNull(ht.get("AYEAR"), ""))+"' "+
        	"AND A.SMS = '"+Utility.dbStr(Utility.checkNull(ht.get("SMS"), ""))+"' "+
        	"AND A.STNO =  '"+Utility.dbStr(Utility.checkNull(ht.get("STNO"), ""))+"' "+
        	"AND A.UNQUAL_TAKE_MK = 'N' "+
        	"AND A.UNTAKECRS_MK = 'N'   "+
        	"";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            if (rs.next()) {
            	rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
                }
               
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return rowHt;
    }
    
    
    /**
     *  取得問卷調查資料....老師首頁用
     *  by poto 20130521 修改可以丟科目給他
     */
    public Vector getCqrDataForTchIndex(Hashtable ht) throws Exception {
    	Vector result = new Vector();    	
    	    	
        String sql =
        	"select a.CLASS_CODE, d.QR_CODE, "+
        		"(select count(1) from cqrt006 d where c.AYEAR=d.AYEAR and c.SMS=d.SMS and c.CRSNO=d.CRSNO and c.QR_CODE=d.QR_CODE and d.STNO='"+Utility.nullToSpace(ht.get("USER_ID"))+"') as IS_WRITE, "+
        		"case "+
		        " when to_char(sysdate, 'yyyymmdd') between c.SCD_CLOSE_SDATE and c.SCD_CLOSE_EDATE then 'N' "+
		        " when c.SCD_OPEN_YN = 'Y' then 'Y' else'N' "+
		        "end as scd_show_mk "+
        	"from plat012 a "+
        	"join cqrt005 c on a.AYEAR=c.AYEAR and a.SMS=c.SMS and a.CRSNO=c.CRSNO and to_char(sysdate,'yyyymmdd') between c.OPEN_DATE and c.COLSE_DATE "+
        	"join cqrt001 d on c.QR_CODE = d.QR_CODE and d.ASSIGN_ID_TYPE ='2' "+
        	"where a.AYEAR='"+Utility.nullToSpace(ht.get("AYEAR"))+"' " +
        	"and a.SMS='"+Utility.nullToSpace(ht.get("SMS"))+"' "+
        	"and a.TCH_IDNO='"+Utility.nullToSpace(ht.get("TCH_IDNO"))+"' "+
        	"and a.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' "+
        	"and a.CLASS_CODE = '" + Utility.nullToSpace(ht.get("CLASS_CODE")) + "' "+
        	"";
        
        
        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql.toString());

            while (rs.next()) {
            	Hashtable rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return result;
    }

    /**
     *  取得某一學年期某個學生的選課資料
     *  @param String ayear
     *  @param String sms
     *  @param String stno
     */
    public String getRegAyearSmsCrd(String ayear,String sms,String stno) throws Exception {
    	String crd = "0";

        String sql =
        	"SELECT "+
        	"SUM(B.CRD) AS CRD "+
        	"FROM REGT007 A "+
        	"JOIN COUT002 B ON B.CRSNO = A.CRSNO "+
        	"WHERE A.AYEAR = '"+Utility.dbStr(ayear)+"' "+
        	"AND A.SMS = '"+Utility.dbStr(sms)+"' "+
        	"AND A.STNO =  '"+Utility.dbStr(stno)+"' "+
        	"AND A.UNQUAL_TAKE_MK = 'N' "+
        	"AND A.UNTAKECRS_MK = 'N'   "+
        	"";

        DBResult rs = null;
        try {
        	rs = dbmanager.getSimpleResultSet(conn);
            rs.open();
            rs.executeQuery(sql);

            if (rs.next()) {
            	crd = Utility.nullToSpace(rs.getString(1));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null)
                rs.close();
        }

        return crd;
    }
    
    
    /**
 	 * JOIN 其它 TABLE 將欄位的值抓出來
 	 *
 	 * @param ht 條件值
 	 * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
 	 *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
 	 * @throws Exception
 	 */
 	public Vector getPla037MQuery(Hashtable ht) throws Exception {
 		Vector result = new Vector();

 		if (sql.length() > 0)
 			sql.delete(0, sql.length());
 		
 		sql.append("select ");
 		sql.append("m.ayear,m.sms,m.stno,m.crsno,m.allocate_cmps_code,m.lab_class_code,m.lab_cmps_code,m.tut_cmps_code,m.tut_class_code,m.ass_class_code,m.master_class_code, ");
 		sql.append("a.crs_name,c.name,e.center_abrcode,e.CENTER_ABBRNAME,s2.center_abbrname||p22.cmps_name as cntr_cmp, ");
 		sql.append("p0.cmps_name as allocate_cmps_name,p1.cmps_name as tut_cmps_name,p2.cmps_name as lab_cmps_name,decode(m.payment_status,'1','未繳費','已繳費') as payment_status ");
 		sql.append("from regt007 m ");
 		sql.append("join cout002 a on m.crsno = a.crsno ");
 		sql.append("join stut003 b on m.stno = b.stno ");
 		sql.append("join stut002 c on b.idno = c.idno and b.birthdate = c.birthdate ");
 		sql.append("left join stut004 d on d.ayear = m.ayear and d.sms = m.sms and d.stno = m.stno ");
 		sql.append("join syst002 e on e.center_code = nvl(d.center_code,b.center_code) ");
 		sql.append("left join plat002 p0 on p0.ayear = m.ayear and p0.sms = m.sms and p0.center_abrcode = e.center_abrcode and p0.cmps_code = m.allocate_cmps_code  ");
 		sql.append("left join plat002 p1 on p1.ayear = m.ayear and p1.sms = m.sms and p1.center_abrcode = e.center_abrcode and p1.cmps_code = m.tut_cmps_code  ");
 		sql.append("left join plat002 p2 on p2.ayear = m.ayear and p2.sms = m.sms and p2.center_abrcode = e.center_abrcode and p2.cmps_code = m.lab_cmps_code  ");
 		sql.append("join syst002 s2 on s2.center_abrcode = substr(m.tut_class_code,1,1) ");
 		sql.append("join plat002 p22 on p22.ayear = m.ayear and p22.sms = m.sms and p22.center_abrcode = substr(m.tut_class_code,1,1) and p22.cmps_code = m.tut_cmps_code ");
 		sql.append("where 1=1 ");
 		sql.append("and m.unqual_take_mk='N' and m.untakecrs_mk='N' ");
 		if (!Utility.nullToSpace(ht.get("AYEAR")).equals(""))
 			sql.append("and m.ayear = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
 		if (!Utility.nullToSpace(ht.get("SMS")).equals(""))
 			sql.append("and m.sms = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
 		if (!Utility.nullToSpace(ht.get("CRSNO")).equals(""))
 			sql.append("and m.crsno = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
 		if (!Utility.nullToSpace(ht.get("STNO")).equals(""))
 			sql.append("and m.stno = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
 		if (!Utility.nullToSpace(ht.get("CENTER_ABRCODE")).equals(""))
 			sql.append("and substr(m.tut_class_code,1,1) = '" + Utility.nullToSpace(ht.get("CENTER_ABRCODE")) + "' ");
 		if (!Utility.nullToSpace(ht.get("CMPS_CODE")).equals(""))
 			sql.append("and m.tut_cmps_code = '" + Utility.nullToSpace(ht.get("CMPS_CODE")) + "' ");
 		if (Utility.nullToSpace(ht.get("PAYMENT_STATUS")).equals("Y")) {
            sql.append("AND m.payment_status !='1' ");
        }
 		
 		DBResult rs = null;
 		try {
 			if (pageQuery) {
 				// 依分頁取出資料
 				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
 			} else {
 				// 取出所有資料
 				rs = dbmanager.getSimpleResultSet(conn);
 				rs.open();
 				rs.executeQuery(sql.toString());
 			}
 			Hashtable rowHt = null;
 			while (rs.next()) {
 				rowHt = new Hashtable();
 				/** 將欄位抄一份過去 */
 				for (int i = 1; i <= rs.getColumnCount(); i++) {
 					rowHt.put(rs.getColumnName(i), rs.getString(i));
 				}
 				result.add(rowHt);
 			}
 		} catch (Exception e) {
 			throw e;
 		} finally {
 			if (rs != null) {
 				rs.close();
 			}
 		}
 		return result;
 	}
    
}

    