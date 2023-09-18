import { useEffect, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import './App.css';

import TestPage from './pages/test-page/TestPage';

// 메인페이지
import MainPage from './pages/main-page/MainPage';
import UserTemplate from './templates/user-template/UserTemplate';
import GuestTemplate from './templates/guest-template/GuestTemplate';
import SettingTemplate from './templates/setting-template/SettingTemplate';
import GoalTemplate from './templates/goal-template/GoalTemplate';
import AnalysisTemplate from './templates/analysis-template/AnalysisTemplate';
import DiaryTemplate from './templates/diary-template/DiaryTemplate';

// 로그인 관련 페이지
import LoginPage from './pages/login-page/LoginPage';
import LoginTemplate from './templates/login-template/LoginTemplate';
import SignUpTemplate from './templates/signup-template/SignUpTemplate';
import RepasswordTemplate from './templates/repassword-template/RepasswordTemplate';

// 은행 계좌 관련 페이지
import BankPage from './pages/bank-page/BankPage';
import BankMainTemplate from './templates/bank-main-template/BankMainTemplate';
import AccountTemplate from './templates/account-template/AccountTemplate';
import RegistrationTemplate from './templates/registration-template/RegistrationTemplate';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');

    if (accessToken) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [location]);

  return (
    <Routes>
      {/* 테스트용 페이지 */}
      <Route path="/test" element={<TestPage />} />

      {/* 메인 페이지 */}
      <Route path="/" element={<MainPage isLoggedIn={isLoggedIn} />}>
        {isLoggedIn ? (
          <Route path="" element={<UserTemplate />} />
        ) : (
          <Route path="" element={<GuestTemplate />} />
        )}
        <Route path="setting" element={<SettingTemplate />} />
        <Route path="goal" element={<GoalTemplate />} />
        <Route path="analysis" element={<AnalysisTemplate />} />
        <Route path="diary" element={<DiaryTemplate />} />
      </Route>

      {/* 로그인 페이지 */}
      <Route path="/login" element={<LoginPage />}>
        <Route path="" element={<LoginTemplate />} />
        <Route path="signup" element={<SignUpTemplate />} />
        <Route path="repassword" element={<RepasswordTemplate />} />
      </Route>

      {/* 계좌 페이지 */}
      <Route path="/bank" element={<BankPage />}>
        <Route path="" element={<BankMainTemplate />} />
        <Route path="account" element={<AccountTemplate />} />
        <Route path="regist" element={<RegistrationTemplate />} />
      </Route>
    </Routes>
  );
}

export default App;
