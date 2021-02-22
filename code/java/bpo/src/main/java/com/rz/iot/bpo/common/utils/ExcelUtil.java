package com.rz.iot.bpo.common.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.rz.iot.bpo.common.constant.DictDataConstants;
import com.rz.iot.bpo.model.show.BpoAttendanceInfoShow;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: chenmingjian
 * @date: 19-3-18 16:16
 */
@Slf4j
public class ExcelUtil {

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);

    }

    /**
     * 读取少于1000行数据
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readLessThan1000Row(String filePath){
        return readLessThan1000RowBySheet(filePath,null);
    }

    /**
     * 读小于1000行数据, 带样式
     * filePath 文件绝对路径
     * initSheet ：
     *      sheetNo: sheet页码，默认为1
     *      headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
     *      clazz: 返回数据List<Object> 中Object的类名
     */
    public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet){
        if(!StringUtils.hasText(filePath)){
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            return EasyExcelFactory.read(fileStream, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(fileStream != null){
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 读大于1000行数据
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000Row(String filePath){
        return readMoreThan1000RowBySheet(filePath,null);
    }

    /**
     * 读大于1000行数据, 带样式
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet){
        if(!StringUtils.hasText(filePath)){
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            return excelListener.getDatas();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(fileStream != null){
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 读大于1000行数据, 带样式
     * @param fileStream 文件流
     * @return
     */
    public static List<Object> readMoreThan1000RowBySheetStream(InputStream fileStream, Sheet sheet){
        /*if(!StringUtils.hasText(filePath)){
            return null;
        }*/

        sheet = sheet != null ? sheet : initSheet;

        // InputStream fileStream = null;
        try {
            // fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            return excelListener.getDatas();
        } catch (Exception e) {
            log.error("文件不存在或路径错误");
        }finally {
            try {
                if(fileStream != null){
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 生成excle
     * @param filePath  绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param head 表头
     */
    public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head){
        writeSimpleBySheet(filePath,data,head,null);
    }

    /**
     * 生成excle
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param sheet excle页面样式
     * @param head 表头
     */
    public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet){
        sheet = (sheet != null) ? sheet : initSheet;

        if(head != null){
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data,sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }

            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成excle
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data){
        writeWithTemplateAndSheet(filePath,data,null);
    }

    /**
     * 生成excle
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param sheet excle页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet){
        if(CollectionUtils.isEmpty(data)){
            return;
        }

        sheet = (sheet != null) ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data,sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成多Sheet的excle
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param multipleSheelPropetys
     */
    public static void writeWithMultipleSheel(String filePath,List<MultipleSheelPropety> multipleSheelPropetys){
        if(CollectionUtils.isEmpty(multipleSheelPropetys)){
            return;
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
                Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
                if(!CollectionUtils.isEmpty(multipleSheelPropety.getData())){
                    sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
                }
                writer.write(multipleSheelPropety.getData(), sheet);
            }

        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }


    /*********************匿名内部类开始，可以提取出去******************************/

    @Data
    public static class MultipleSheelPropety{

        private List<? extends BaseRowModel> data;

        private Sheet sheet;
    }

    /**
     * 解析监听器，
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     *
     * @author: chenmingjian
     * @date: 19-4-3 14:11
     */
    @Getter
    @Setter
    public static class ExcelListener extends AnalysisEventListener {

        private List<Object> datas = new ArrayList<>();

        /**
         * 逐行解析
         * object : 当前行的数据
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            // context.getCurrentRowNum()
            if (object != null) {
                datas.add(object);
            }
        }


        /**
         * 解析完所有数据后会调用该方法
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
        }

    }

    /************************匿名内部类结束，可以提取出去***************************/

    /**
     * excel导出（写excel）
     * @param out 输出流
     * @param data 数据
     * @param headLineMun 表头占的行数
     * @param columnWidth 列宽
     * @param clazz 模型Class
     * @param <T> 泛型
     */
    public static <T extends BaseRowModel> void writeExl(OutputStream out, List<T> data, Integer headLineMun, Integer columnWidth, Class<T> clazz) {

        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        //写第一个sheet, sheet1
        Sheet sheet1 = new Sheet(1, headLineMun, clazz);
        sheet1.setSheetName("Sheet1");

        Map columnWidthMap = new HashMap();

        for (int i = 0; i < headLineMun; i++) {
            columnWidthMap.put(i, columnWidth);
        }
        // 设置列宽 设置每列的宽度
        sheet1.setColumnWidthMap(columnWidthMap);
        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);

        writer.write(data, sheet1);
        writer.finish();
    }


    /**
     *  考勤模块导出
     * @return
     */
    public static HSSFWorkbook outAttendanceInfoBook(List<BpoAttendanceInfoShow> list){
        HSSFWorkbook wb = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
            wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet");
            int num = 0;
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(HorizontalAlignment.CENTER);// 创建一个居中格式
            // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);

            Cell cell = row.createCell(0);
            cell.setCellValue("姓名/工号");
            cell.setCellStyle(cs);

            cell = row.createCell(1);
            cell.setCellValue("考勤日期");
            cell.setCellStyle(cs);

            cell = row.createCell(2);
            cell.setCellValue("岗位");
            cell.setCellStyle(cs);

            cell = row.createCell(3);
            cell.setCellValue("项目");
            cell.setCellStyle(cs);

            cell = row.createCell(4);
            cell.setCellValue("班次信息");
            cell.setCellStyle(cs);

            cell = row.createCell(5);
            cell.setCellValue("到场时间");
            cell.setCellStyle(cs);

            cell = row.createCell(6);
            cell.setCellValue("上班时间");
            cell.setCellStyle(cs);

            cell = row.createCell(7);
            cell.setCellValue("下班时间");
            cell.setCellStyle(cs);

            cell = row.createCell(8);
            cell.setCellValue("排班工时");
            cell.setCellStyle(cs);

            cell = row.createCell(9);
            cell.setCellValue("扣除时长");
            cell.setCellStyle(cs);

            cell = row.createCell(10);
            cell.setCellValue("上班打卡时间");
            cell.setCellStyle(cs);

            cell = row.createCell(11);
            cell.setCellValue("下班打卡时间");
            cell.setCellStyle(cs);

            cell = row.createCell(12);
            cell.setCellValue("考勤时长");
            cell.setCellStyle(cs);

            cell = row.createCell(13);
            cell.setCellValue("班次工时");
            cell.setCellStyle(cs);

            cell = row.createCell(14);
            cell.setCellValue("加班工时");
            cell.setCellStyle(cs);

            cell = row.createCell(15);
            cell.setCellValue("系统判定状态");
            cell.setCellStyle(cs);

            cell = row.createCell(16);
            cell.setCellValue("审核计薪工时");
            cell.setCellStyle(cs);

            cell = row.createCell(17);
            cell.setCellValue("是否审核");
            cell.setCellStyle(cs);

            cell = row.createCell(18);
            cell.setCellValue("审核状态");
            cell.setCellStyle(cs);

            cell = row.createCell(19);
            cell.setCellValue("说明");
            cell.setCellStyle(cs);

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    row = sheet.createRow((int) i + 1);
                } else {
                    row = sheet.createRow((int) num + 1);
                }
                BpoAttendanceInfoShow info = list.get(i);
                row.createCell(0).setCellValue(info.getPersonName() + "/" + info.getWorkNo());
                row.createCell(1).setCellValue(info.getAttendanceDate() == null ? "" : sdf.format(info.getAttendanceDate()));
                row.createCell(2).setCellValue(info.getStation() == null ? "" : info.getStation());
                row.createCell(3).setCellValue(info.getProjectName() == null ? "" : info.getProjectName());
                row.createCell(4).setCellValue(info.getClassSn() == null ? "" : info.getClassSn());
                row.createCell(5).setCellValue(info.getArrivalTime() == null ? "" : sdf1.format(info.getArrivalTime()));
                row.createCell(6).setCellValue(info.getStartTime() == null ? "" : sdf1.format(info.getStartTime()));
                row.createCell(7).setCellValue(info.getEndTime() == null ? "" : sdf1.format(info.getEndTime()));
                row.createCell(8).setCellValue(info.getWorkingHours() == null ? 0 : info.getWorkingHours());
                row.createCell(9).setCellValue(info.getExcludeHour() == null ? 0 : info.getExcludeHour());
                row.createCell(10).setCellValue(info.getClockInTime() == null ? "" : sdf1.format(info.getClockInTime()));
                row.createCell(11).setCellValue(info.getClockOutTime() == null ? "" : sdf1.format(info.getClockOutTime()));
                row.createCell(12).setCellValue(info.getAttendanceHours() == null ? 0 : info.getAttendanceHours());
                row.createCell(13).setCellValue(info.getWageHours() == null ? 0 : info.getWageHours());
                row.createCell(14).setCellValue(info.getOvertimeHours() == null ? 0 : info.getOvertimeHours());
                row.createCell(15).setCellValue(attendanceStatus(info.getRecordStatus()));
                row.createCell(16).setCellValue(info.getApprovalWageHours() == null ? 0 : info.getApprovalWageHours());
                row.createCell(17).setCellValue(recordApprovalStatus(info.getRecordApprovalStatus()));
                row.createCell(18).setCellValue(attendanceStatus(info.getApprovalStatus()));
                row.createCell(19).setCellValue(info.getRemark() == null ? "" : info.getRemark());

                num = row.getRowNum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static String attendanceStatus(Byte status){
        if (DictDataConstants.ATTENDANCE_NORMAL.equals(status)) {
            return "正常";
        }
        if (DictDataConstants.ATTENDANCE_LATE.equals(status)) {
            return "迟到";
        }
        if (DictDataConstants.ATTENDANCE_LEAVE_EARLY.equals(status)) {
            return "早退";
        }
        if (DictDataConstants.ATTENDANCE_LACK_CLOCK_IN.equals(status)) {
            return "缺上班卡";
        }
        if (DictDataConstants.ATTENDANCE_LACK_CLOCK_OUT.equals(status)) {
            return "缺下班卡";
        }
        if (DictDataConstants.ATTENDANCE_OVERTIME_TOO_LONG.equals(status)) {
            return "工时过长";
        }
        if (DictDataConstants.ATTENDANCE_NEGLECT_WORK.equals(status)) {
            return "旷工";
        }
        if (DictDataConstants.ATTENDANCE_OVERTIME.equals(status)) {
            return "加班";
        }
        if (DictDataConstants.ATTENDANCE_TEMPORARY_LEAVE.equals(status)) {
            return "临时请假";
        }
        if (DictDataConstants.ATTENDANCE_HOLIDAY.equals(status)) {
            return "休假";
        }
        return "";
    }

    private static String recordApprovalStatus(Byte status){
        if (DictDataConstants.PENDING_REVIEW.equals(status)) {
            return "待审核";
        }
        if (DictDataConstants.PENDING_CONFIRM.equals(status)) {
            return "待确认";
        }
        if (DictDataConstants.CONFIRMED.equals(status)) {
            return "已确认";
        }
        return "";
    }

}

