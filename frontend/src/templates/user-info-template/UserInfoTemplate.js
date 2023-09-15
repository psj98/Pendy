import React, { useState } from 'react';
import './UserInfoTemplate.css';
import { Icon } from '@iconify/react';
import handleSignup from '../../utils/handleSignup';
import { useNavigate } from 'react-router-dom';
import AccountModal from '../../components/signup/account-modal/AccountModal';

const UserInfoTemplate = () => {
  const [state, setState] = useState({
    email: '',
    password: '',
    repassword: '',
    name: '',
    age: '',
    salary: '',
    accounts: [{ accountNumber: '', bankCode: '' }],
  });

  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  //모달창 열기
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기
  const closeModal = () => {
    setIsModalOpen(false);
  };

  //계좌 개수 증가
  const handleAddAccount = () => {
    setState({ ...state, accounts: [...state.accounts, ''] });
  };

  //계좌 삭제
  const handleRemoveAccount = (index) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts.splice(index, 1);
    setState({ ...state, accounts: updatedAccounts });
  };

  //계좌 index 변경
  const handleAccountChange = (index, value) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts[index] = value;
    setState({ ...state, accounts: updatedAccounts });
  };

  //비밀번호 재확인 성공여부 판단
  const passwordCheck = state.password === state.repassword;

  // 회원가입 버튼 클릭 시
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
        navigate('/login', { replace: true });
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
      <p className="signup-title">회원가입</p>

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

      <div className="signup-input-main">
        {/* 계좌 입력 */}
        {state.accounts.map((account, index) => (
          <div key={index} className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder={`계좌 ${index + 1} 계좌번호`}
              variant="outlined"
              value={account.accountNumber}
              onChange={(e) =>
                handleAccountChange(index, 'accountNumber', e.target.value)
              }
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
        ))}
        {/* 은행 선택 */}
        {state.accounts.map((account, index) => (
          <div key={index} className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder={`은행 ${index + 1} 코드`}
              variant="outlined"
              value={account.bankCode}
              onChange={(e) =>
                handleAccountChange(index, 'bankCode', e.target.value)
              }
            />
            <span className="border"></span>
          </div>
        ))}
      </div>

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

      {/* 모달 창 */}
      {isModalOpen && (
        <AccountModal
          state={state}
          closeModal={closeModal}
          handleAccountChange={handleAccountChange}
        />
      )}
    </div>
  );
};

export default UserInfoTemplate;
