import React, { useState } from 'react';
import './SignUpTemplate.css';
import { Icon } from '@iconify/react';
import handleSignup from '../../utils/handleSignup';
import AccountModal from '../../components/signup/account-modal/AccountModal';

const SignUpTemplate = () => {
  const [state, setState] = useState({
    email: '',
    password: '',
    repassword: '',
    name: '',
    age: '',
    salary: '',
    accounts: [{ accountNumber: '', bankCode: '' }],
  });

  const accountList = JSON.stringify(state.accounts);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedAccountIndex, setSelectedAccountIndex] = useState(null);

  //계좌 개수 증가
  const handleAddAccount = () => {
    setState({
      ...state,
      accounts: [...state.accounts, { accountNumber: '', bankCode: '' }],
    });
  };

  //계좌 삭제
  const handleRemoveAccount = (index) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts.splice(index, 1);
    setState({ ...state, accounts: updatedAccounts });
  };

  //계좌 index 변경
  const handleAccountChange = (index, key, value) => {
    const updatedAccounts = [...state.accounts];
    updatedAccounts[index][key] = value;
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
        navigator('/login');
      } catch (error) {
        console.error('SignUp failed:', error);
        alert('회원가입에 실패하셨습니다.');
      }
    } else {
      alert('비밀번호가 일치하지 않습니다');
    }
  };

  //모달창 열기
  const openModal = (index) => {
    setSelectedAccountIndex(index);
    setIsModalOpen(true);
  };

  // 모달 닫기
  const closeModal = () => {
    setIsModalOpen(false);
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

      {state.accounts.map((account, index) => (
        <div key={index} className="signup-input-main">
          {/* 계좌 입력 */}
          <div className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder={`계좌 ${index + 1}`}
              variant="outlined"
              value={account.accountNumber}
              onClick={() => openModal(index)}
            />
            <span className="border"></span>
          </div>
          &nbsp;&nbsp;
          {/* 은행 선택 */}
          <div className="signup-input-sub">
            <input
              type="number"
              className="input"
              placeholder={'은행'}
              variant="outlined"
              value={account.bankCode}
              onClick={() => openModal(index)}
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
      {/* 모달 창 */}
      {isModalOpen && (
        <AccountModal
          accountList={accountList}
          closeModal={closeModal}
          handleAccountChange={handleAccountChange}
          index={selectedAccountIndex}
        />
      )}
    </div>
  );
};

export default SignUpTemplate;
