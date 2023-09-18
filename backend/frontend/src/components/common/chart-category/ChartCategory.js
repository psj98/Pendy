import React from 'react';
import './ChartCategory.css';

import GoalBar from '../goal-bar/GoalBar';
import CategoryName from '../category-name/CategoryName';

const ChartCategory = () => {
  return (
    <div className="chart-category">
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="#FAF2E8" name={'식비'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="#BDECEA" name={'교통'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="#DAB8F1" name={'온라인 쇼핑'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName
            color="rgba(243, 213, 182, 0.63)"
            name={'오프라인 쇼핑'}
          />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="#DCF5F5" name={'카페/간식'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="rgba(208, 228, 197, 0.42)" name={'고정지출'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="rgba(255, 170, 180, 0.50)" name={'패션/미용'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
      <div className="category-bar">
        <div className="category-bar-left">
          <CategoryName color="#CFE4C5" name={'문화/여가'} />
        </div>
        <div className="category-bar-right">
          <GoalBar />
        </div>
      </div>
    </div>
  );
};

export default ChartCategory;
