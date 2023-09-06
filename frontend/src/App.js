import { Route, Routes } from 'react-router-dom';
import './App.css';

import TestPage from './pages/test-page/TestPage';

import MainPage from './pages/main-page/MainPage';
import UserTemplate from './templates/user-template/UserTemplate';
import SettingTemplate from './templates/setting-template/SettingTemplate';
import GoalTemplate from './templates/goal-template/GoalTemplate';
import DiaryTemplate from './templates/diary-template/DiaryTemplate';
import GuestTemplate from './templates/guest-template/GuestTemplate';
import AnalysisTemplate from './templates/analysis-template/AnalysisTemplate';

import LoginPage from './pages/login-page/LoginPage';
import LoginTemplate from './templates/login-template/LoginTemplate';
import SignUpTemplate from './templates/signup-template/SignUpTemplate';
import RepasswordTemplate from './templates/repassword-template/RepasswordTemplate';

import BankPage from './pages/bank-page/BankPage';
import BankMainTemplate from './templates/bank-main-template/BankMainTemplate';
import AccountTemplate from './templates/account-template/AccountTemplate';
import RegistrationTemplate from './templates/registration-template/RegistrationTemplate';

function App() {
  const isLoggedIn = true;

  return (
    <Routes>
      {/* 테스트용 페이지 */}
      <Route path="/test" element={<TestPage />} />

      {/* 메인 페이지 */}
      <Route path="/" element={<MainPage />}>
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
