package com.bst.backcommon.io.excel;


import com.bst.backcommon.util.QuietlyUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wanna
 */
public class ExcelReader implements AutoCloseable {

    private Workbook book;

    private Sheet sheet;

    public ExcelReader(String file) throws Exception {
        this(new File(file));
    }

    public ExcelReader(File file) throws Exception {
        this(new FileInputStream(file));
    }

    public ExcelReader(InputStream stream) throws Exception {
        book = WorkbookFactory.create(stream);
        openTable(0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void openTable(int index) {
        sheet = book.getSheetAt(index);
    }

    public void openTable(String name) {
        sheet = book.getSheet(name);
    }

    public int getSheetCount() {
        return book.getNumberOfSheets();
    }

    /**
     * 获取 sheet 行数
     * @param rowIndex 行
     * @return
     */
    public int getRowCount(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        return row == null ? 0 : row.getLastCellNum();

    }

    /**
     * 获取指定行的列数
     *
     * @return
     */
    public int getColumnCount() {
        return sheet.getLastRowNum()+1;
    }

    /**
     * 获取指定的行内容
     *
     * @param rowIndex 行索引
     * @return 每一行内容
     */
    public List<Cell> getTableRow(int rowIndex) {
        List<Cell> result = new ArrayList<>();
        Row row = sheet.getRow(rowIndex);

        Iterator<Cell> iterator = row.cellIterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }

    public List<Cell> getTableColumn(int columnIndex) {
        List<Cell> result = new ArrayList<>();
//        sheet.getCol
        return null;

    }

    // 转换成其他 Bean 待实现 ....

    public void close() {
        QuietlyUtil.close(book);
    }
}
