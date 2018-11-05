package com.bst.backcommon.io.excel;

import com.bst.backcommon.util.QuietlyUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wanna
 */
public class ExcelWriter implements AutoCloseable {

    private final static int COLUMN_UNIT = 256;

    private final static short ROW_UNIT = 20;

    private Workbook book;

    private Sheet sheet;

    private CellStyle style;

    private Position position;

    public ExcelWriter(InputStream stream) throws Exception {
        book = WorkbookFactory.create(stream);
        sheet = book.getSheetAt(0);
        style = createCellStyle();
        position = new Position(0, 0);
    }

    /**
     * 删除指定 sheet
     *
     * @param index sheet 索引
     */
    public void deleteSheet(int index) {
        book.removeSheetAt(index);
    }

    /**
     * 创建 sheet 内部算法决定
     */
    public void createSheet() {
        sheet = book.createSheet();
        initSheet();
    }

    /**
     * 创建 指定名字的  sheet
     *
     * @param name sheet 名字
     */
    public void createSheet(String name) {
        sheet = book.createSheet(name);
        initSheet();
    }

    /**
     * 创建 sheet 时初始化 sheet 的相关属性
     */
    private void initSheet() {
        position = new Position(0, 0);
        style = createCellStyle();
    }

    /**
     * 设置打印输出居中
     *
     * @param horizonCenter  水平居中
     * @param verticalCenter 垂直居中
     */
    public void setSheetOutputAlignment(boolean horizonCenter, boolean verticalCenter) {
        sheet.setHorizontallyCenter(horizonCenter);
        sheet.setVerticallyCenter(verticalCenter);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 自动设置列宽度
     *
     * @param index 列索引
     */
    public void autoSizeColumn(int index) {
        autoSizeColumn(index, false);
    }

    /**
     * 自动设置列宽度
     *
     * @param index          列索引
     * @param useMergedCells 是否对合并的单元格有效
     */
    public void autoSizeColumn(int index, boolean useMergedCells) {
        sheet.autoSizeColumn(index, useMergedCells);
    }

    /**
     * 设置列宽度
     *
     * @param columnIndex 列索引
     * @param width       宽度
     */
    public void setColumnWidth(int columnIndex, int width) {
        sheet.setColumnWidth(columnIndex, width * COLUMN_UNIT);
    }

    /**
     * 设置行高度
     *
     * @param height
     */
    public void setRowHeight(int height) {
        sheet.setDefaultRowHeight((short) (height * ROW_UNIT));
    }

    /**
     * 创建字体
     *
     * @param fontName
     * @return
     */
    public Font createFont(String fontName) {
        Font font = book.createFont();
        font.setFontName(fontName);
        return font;
    }

    /**
     * 创建单元格样式
     *
     * @return
     */
    public CellStyle createCellStyle() {
        CellStyle cellStyle = book.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    /**
     * 合并单元格
     * 需要移动位置 move()
     *
     * @param firstRow 起始行
     * @param lastRow  结束行
     * @param firstCol 起始列
     * @param lastCol  结束列
     */
    public void mergeCells(int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 设置表头
     *
     * @param columnIndex 列索引
     * @param name        列名
     */
    public void setTableHeader(int columnIndex, String name) {
        int rowIndex = position.getRowIndex();
        Row row = getCurrentRow(rowIndex);
        Cell cell = getCurrentCell(row, columnIndex);
        setCellValue(cell, name);
        cell.setCellStyle(style);
    }

    /**
     * 移动
     *
     * @param directions 方向
     */
    public void move(Direction... directions) {
        for (Direction direction : directions) {
            move(direction);
        }
    }

    /**
     * @param direction
     */
    public void move(Direction direction) {
        switch (direction) {
            case Up:
                position.updateRowIndex(-1);
                break;
            case Down:
                position.updateRowIndex(1);
                break;
            case Left:
                position.updateColumnIndex(-1);
                break;
            case Right:
                position.updateColumnIndex(1);
                break;
            default:
                break;
        }
    }

    /**
     * 按行保存数据1
     *
     * @param data
     */
    public void saveDataByRow(List<?> data) {
        Row row = getCurrentRow(position.getRowIndex());
        for (Object val : data) {
            Cell cell = getCurrentCell(row, position.getColumnIndex());
            setValueAndStyle(val, cell);
            move(Direction.Right);
        }
        move(Direction.Down);
        position.updateColumnIndex(-data.size());
    }

    /**
     * 案列保存数据
     *
     * @param data
     */
    public void saveDataByColumn(List<?> data) {
        int columnIndex = position.getColumnIndex();
        for (Object val : data) {
            Row row = getCurrentRow(position.getRowIndex());
            Cell cell = getCurrentCell(row, columnIndex);
            setValueAndStyle(val, cell);
            move(Direction.Down);
        }
        move(Direction.Right);
        position.updateRowIndex(-data.size());
    }

    /**
     * 窗口冻结
     *
     * @param rowIndex    行索引
     * @param columnIndex 列索引
     */
    public void freeze(int rowIndex, int columnIndex) {
        sheet.createFreezePane(columnIndex, rowIndex);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取当前待操作的 cell
     *
     * @param row         行
     * @param columnIndex 列索引
     * @return
     */
    private Cell getCurrentCell(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        return cell == null ? row.createCell(columnIndex) : cell;
    }

    /**
     * 获取当前行
     *
     * @param rowIndex 行索引
     * @return
     */
    private Row getCurrentRow(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        return row == null ? sheet.createRow(rowIndex) : row;
    }

    /**
     * 设置单元格值
     *
     * @param cell  单元格
     * @param value 值
     */
    private void setCellValue(Cell cell, Object value) {
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Number) {
            cell.setCellValue(String.valueOf(value));
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else if (value instanceof RichTextString) {
            cell.setCellValue((RichTextString) value);
        } else {
            cell.setCellErrorValue((Byte) value);
        }
    }

    /**
     * 设置值和样式
     *
     * @param val  值
     * @param cell cell
     */
    private void setValueAndStyle(Object val, Cell cell) {
        setCellValue(cell, val);
        cell.setCellStyle(style);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 保存
     *
     * @param filePath 文件保存路径
     * @throws IOException
     */
    public void save(String filePath) throws IOException {
        save(new File(filePath));
    }

    /**
     * 保存
     *
     * @param file 文件
     * @throws IOException
     */
    public void save(File file) throws IOException {
        book.write(FileUtils.openOutputStream(file));
    }

    /**
     * 关闭资源
     */
    public void close() {
        QuietlyUtil.close(book);
    }
}
