package com.bst.backcommon.io.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示 excel sheet 中 一个 Cell 位置
 *
 * @author wanna
 * @date 2018-5-22
 */
@Data
@AllArgsConstructor
public class Position {

    private int rowIndex;

    private int columnIndex;

    public void updateRowIndex(int count) {
        this.rowIndex = rowIndex + count;
    }

    public void updateColumnIndex(int count) {
        this.columnIndex = columnIndex + count;
    }
}
