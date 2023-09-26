import { Route, Routes } from 'react-router-dom';
import './App.css';

import useLogin from './hooks/useLogin';

// 테스트 페이지
import TestPage from './pages/test-page/TestPage';

// 메인 페이지
import MainPage from './pages/main-page/MainPage';
import UserTemplate from './templates/user-template/UserTemplate';
import GuestTemplate from './templates/guest-template/GuestTemplate';
import SettingTemplate from './templates/setting-template/SettingTemplate';
import GoalTemplate from './templates/goal-template/GoalTemplate';
import AnalysisTemplate from './templates/analysis-template/AnalysisTemplate';
import DiaryTemplate from './templates/diary-template/DiaryTemplate';
import FirstGoalTemplate from './templates/first-goal-template/FirstGoalTemplate';
import EmotionTemplate from './templates/emotion-template/EmotionTemplate';

// 로그인 페이지
import LoginPage from './pages/login-page/LoginPage';
import LoginTemplate from './templates/login-template/LoginTemplate';
import SignUpTemplate from './templates/signup-template/SignUpTemplate';
import RepasswordTemplate from './templates/repassword-template/RepasswordTemplate';

// 은행 계좌 페이지
import BankPage from './pages/bank-page/BankPage';
import BankMainTemplate from './templates/bank-main-template/BankMainTemplate';
import BankLoginTemplate from './templates/bank-login-template/BankLoginTemplate';
import BankSignupTemplate from './templates/bank-signup-template/BankSignupTemplate';
import AccountTemplate from './templates/account-template/AccountTemplate';
import RegistrationTemplate from './templates/registration-template/RegistrationTemplate';

function App() {
  const isLoggedIn = useLogin();

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
        <Route path="diary/:id" element={<DiaryTemplate />} />
        <Route path="first-goal" element={<FirstGoalTemplate />} />
        <Route path="emotion" element={<EmotionTemplate />} />
      </Route>

      {/* 로그인 페이지 */}
      <Route path="/login" element={<LoginPage isLoggedIn={isLoggedIn} />}>
        <Route path="" element={<LoginTemplate />} />
        <Route path="signup" element={<SignUpTemplate />} />
        <Route path="repassword" element={<RepasswordTemplate />} />
      </Route>

      {/* 계좌 페이지 */}
      <Route path="/bank" element={<BankPage />}>
        <Route path="" element={<BankMainTemplate />} />
        <Route path="login" element={<BankLoginTemplate />} />
        <Route path="signup" element={<BankSignupTemplate />} />
        <Route path="account/:accountNumber" element={<AccountTemplate />} />
        <Route
          path="regist/:accountNumber"
          element={<RegistrationTemplate />}
        />
      </Route>
    </Routes>
  );
}

export default App;
