// 카테고리 요소
import React from 'react';
import './CategoryName.css';

const CategoryName = ({ color, name }) => {
  return (
    <div className="category-name">
      {/* 카테고리 색상 */}
      <div className="square" style={{ backgroundColor: color }} />
      &nbsp;
      {/* 카테고리 이름 */}
      <div>{name}</div>
    </div>
  );
};

export default CategoryName;
