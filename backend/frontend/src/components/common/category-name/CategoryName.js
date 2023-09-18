import React from 'react';
import './CategoryName.css';

const CategoryName = ({ color, name }) => {
  return (
    <div className="category-name">
      <div className="square" style={{ backgroundColor: color }} />
      &nbsp;
      <div>{name}</div>
    </div>
  );
};

export default CategoryName;
