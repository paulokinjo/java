package com.starfish.Utils;

public class Coordinates {
  private int _row;
  private int _column;

  public Coordinates(int row, int column) {
    setRow(row);
    setColumn(column);
  }

  public void setColumn(int column) {
    _column = column;
  }

  public int getColumn() {
    return _column;
  }

  public void setRow(int row) {
    _row = row;
  }

  public int getRow() {
    return _row;
  }
}