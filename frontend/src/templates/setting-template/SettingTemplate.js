import React, { useState } from 'react';
import './SettingTemplate.css';
import { Icon } from '@iconify/react';
import handleSignup from '../../utils/handleSignup';

const SettingTemplate = () => {
  const [state, setState] = useState({
    email: '',
    password: '',
    repassword: '',
    name: '',
    age: '',
    salary: '',
    accounts: [{ accountNumber: '', bankCode: '' }],
  });

  const handleAddAccount = () => {
    setState({ ...state, accounts: [...state.accounts, ''] });
  };

  const handleRemoveAccount = (index) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts.splice(index, 1);
    setState({ ...state, accounts: updatedAccounts });
  };

  const handleAccountChange = (index, value) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts[index] = value;
    setState({ ...state, accounts: updatedAccounts });
  };

  const passwordCheck = state.password === state.repassword;

  const onSignUpButtonClick = async (event) => {
    event.preventDefault();
    if (passwordCheck) {
      try {
        const response = await handleSignup(
          state.email,
          state.password,
          state.name,
          state.age,
          state.salary,
          state.accounts,
        );
        const token = response.data.token;
        console.log('SignUp success', token);
        alert('회원가입에 성공하셨습니다.');
        navigator('/login');
      } catch (error) {
        console.error('SignUp failed:', error);
        alert('회원가입에 실패하셨습니다.');
      }
    } else {
      alert('비밀번호가 일치하지 않습니다');
    }
  };

  return (
    <div className="signup">
      <p className="signup-title">개인 정보 수정</p>

      {/* 이메일 입력 */}
      <div className="signup-input">
        <input
          type="email"
          className="input"
          placeholder="이메일"
          variant="outlined"
          value={state.email}
          onChange={(e) => setState({ ...state, email: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 비밀번호 입력 */}
      <div className="signup-input">
        <input
          type="password"
          className="input"
          placeholder="비밀번호"
          variant="outlined"
          value={state.password}
          onChange={(e) => setState({ ...state, password: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 비밀번호 확인 */}
      <div className="signup-input">
        <input
          type="password"
          className="input"
          placeholder="비밀번호 확인"
          variant="outlined"
          value={state.repassword}
          onChange={(e) => setState({ ...state, repassword: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 이름 입력 */}
      <div className="signup-input">
        <input
          type="text"
          className="input"
          placeholder="이름"
          variant="outlined"
          value={state.name}
          onChange={(e) => setState({ ...state, name: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 나이 입력 */}
      <div className="signup-input-main">
        <div className="signup-input-sub">
          <input
            type="number"
            className="input"
            placeholder="나이"
            variant="outlined"
            value={state.age}
            onChange={(e) => setState({ ...state, age: e.target.value })}
          />
          <span className="border"></span>
        </div>
        &nbsp;&nbsp;
        {/* 소득 입력 */}
        <div className="signup-input-sub">
          <input
            type="number"
            className="input"
            placeholder="연봉"
            variant="outlined"
            value={state.salary}
            onChange={(e) => setState({ ...state, salary: e.target.value })}
          />
          <span className="border"></span>
        </div>
      </div>

      {/* 계좌 입력 */}
      {state.accounts.map((accountNumber, index) => (
        <div className="signup-input-main">
          <div className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder={`계좌 ${index + 1}`}
              variant="outlined"
              value={accountNumber}
              onChange={(e) => handleAccountChange(index, e.target.value)}
            />
            <span className="border"></span>
            {/* 계좌 제거 버튼 */}
            {index > 0 && (
              <Icon
                icon="ph:minus-fill"
                color="#007bff"
                width="30"
                height="30"
                onClick={() => handleRemoveAccount(index)}
              />
            )}
          </div>
          &nbsp;&nbsp;
          {/* 은행 번호 입력 */}
          <div className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder="은행"
              variant="outlined"
              value={state.salary}
              onChange={(e) => setState({ ...state, salary: e.target.value })}
            />
            <span className="border"></span>
          </div>
        </div>
      ))}
      {/* 계좌 추가 버튼 */}
      <div className="signup-input">
        <Icon
          icon="ph:plus-fill"
          color="#007bff"
          width="30"
          height="30"
          onClick={handleAddAccount}
        />
      </div>
      <br />
      <div className="signup-input">
        <button className="signup-button" onClick={onSignUpButtonClick}>
          가입하기
        </button>
      </div>
    </div>
  );
};

export default SettingTemplate;
