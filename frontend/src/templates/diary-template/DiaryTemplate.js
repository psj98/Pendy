import React from 'react';
import './DiaryTemplate.css';
import DiaryForm from '../../components/diary/DiaryForm';
import DonutChart from '../../components/common/donut-chart/DonutChart';
import GoalBar from '../../components/common/goal-bar/GoalBar';
import useDiaryDetail from '../../hooks/useDiaryDetail';
import useTodayList from '../../hooks/useTodayList';
import EmotionModal from '../../components/modal/emotion-modal/EmotionModal';
import { useParams } from 'react-router-dom';
import { isSameDay, parseISO } from 'date-fns';

const DiaryTemplate = () => {
  const { id, diaryDate } = useParams();
  const { diaryDetail, diaryLoading } = useDiaryDetail(id, diaryDate);
  const { todayList, todayLoading } = useTodayList(diaryDate);

  const categoryNameToKor = {
    food: '식비',
    traffic: '교통',
    online: '온라인 쇼핑',
    offline: '오프라인 쇼핑',
    cafe: '카페/간식',
    housing: '고정지출',
    fashion: '패션/미용',
    culture: '문화/여가',
  };

  if (diaryLoading) {
    return <div>Loading...</div>;
  }
  if (todayLoading) {
    return <div>Loading...</div>;
  }

  let isModalOpen = false;
  const regDate = diaryDetail.data.diary.regDate;
  const title = diaryDetail.data.diary.title;
  const content = diaryDetail.data.diary.content;
  const comment = diaryDetail.data.diary.comment;
  const stampType = diaryDetail.data.diary.stampType;

  const diaryDay = parseISO(diaryDate);
  if (isSameDay(new Date(), diaryDay)) {
    if (todayList.data.length !== 0) {
      console.log('modal open');
      isModalOpen = true;
    }
  }

  const series = diaryDetail.data.dailyStatistic.amountByCategory.map(
    (item) => item.amount,
  );

  const categoryNames = diaryDetail.data.dailyStatistic.amountByCategory.map(
    (item) => item.categoryName,
  );

  console.log(diaryDetail);

  const chartColors = [
    '#FAF2E8',
    '#BDECEA',
    '#DAB8F1',
    'rgba(243, 213, 182, 0.63)',
    'rgba(208, 228, 197, 0.42)',
    'rgba(255, 170, 180, 0.50)',
    '#CFE4C5',
    'rgba(189, 236, 235, 0.53)',
  ];

  // 해당 월의 마지막날 구하기
  const dateObject = new Date(regDate);
  dateObject.setMonth(dateObject.getMonth() + 1, 0);
  const lastDayOfMonth = dateObject.getDate();

  // 모달 닫기
  const closeModal = () => {
    isModalOpen = false;
  };

  return (
    <div className="diary-container">
      <div className="diary-template">
        {/* 일기장 */}
        <div className="diary-form-container">
          <h1 className="diary-title-name">일기장</h1>
          <DiaryForm
            regDate={regDate}
            title={title}
            content={content}
            comment={comment}
            stampType={stampType}
          />
        </div>

        {/* 도넛 차트 */}
        <div className="diary-donutchart-container">
          <h1 className="diary-donutchart-title">오늘 소비 분석</h1>
          <DonutChart
            series={series}
            title={'오늘 총 소비액'}
            legendShow={false}
            legendFont={20}
            labelShow={true}
            labelFont={18}
            labelColor={'black'}
            valueFont={16}
            valueShow={true}
            valueColor={'black'}
            colors={chartColors}
          />

          {/* 카테고리 */}
          <div className="category-list-cotainer">
            <div className="category-left">
              {categoryNames.slice(0, 4).map((category, index) => (
                <div key={index} className="goal-inputs-category">
                  <div
                    className="goal-inputs-rectangle-label"
                    style={{
                      backgroundColor: chartColors[index],
                    }}
                  ></div>
                  <div className="goal-inputs-category-name donut-category-name">
                    {categoryNameToKor[category]}
                  </div>
                </div>
              ))}
            </div>
            <div className="category-right">
              {categoryNames.slice(4, 8).map((category, index) => (
                <div key={index} className="goal-inputs-category">
                  <div
                    className="goal-inputs-rectangle-label"
                    style={{
                      backgroundColor: chartColors[index + 4],
                    }}
                  ></div>
                  <div className="goal-inputs-category-name donut-category-name">
                    {categoryNameToKor[category]}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* 상태 바 */}
        <div className="diary-goal-container">
          <h1>남은 목표 금액</h1>
          {diaryDetail.data.goalByCategory.map((goalByCategory, index) => (
            <div key={index} className="diary-goal">
              <div
                className="goal-inputs-rectangle-label"
                style={{
                  backgroundColor: chartColors[index],
                }}
              ></div>
              <GoalBar
                color={chartColors[index]}
                current={series[index]}
                goal={goalByCategory.categoryGoalAmount / lastDayOfMonth}
                type={'diary'}
                textcolor={
                  series[index] >=
                  goalByCategory.categoryGoalAmount / lastDayOfMonth
                    ? 'red'
                    : 'black'
                }
              />
            </div>
          ))}
        </div>
      </div>

      {/* 모달 창 */}
      {isModalOpen && (
        <EmotionModal closeModal={closeModal} todayList={todayList} />
      )}
    </div>
  );
};

export default DiaryTemplate;
