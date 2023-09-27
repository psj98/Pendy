import React, { useState } from 'react';
import './SignUpTemplate.css';
import { Icon } from '@iconify/react';
import handleSignup from '../../utils/handleSignup';
import AccountModal from '../../components/modal/account-modal/AccountModal';
import { useNavigate } from 'react-router-dom';
import handleDuplicate from '../../utils/handleCheckDuplicate';

const SignUpTemplate = () => {
  const [state, setState] = useState({
    email: '',
    password: '',
    repassword: '',
    name: '',
    age: '',
    salary: '',
    accounts: [],
    banks: [],
  });

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedAccountIndex, setSelectedAccountIndex] = useState(null);

  const navigate = useNavigate();

  // 이메일에서 Enter 키를 누른 경우 checkDuplicateEmail 함수 실행
  const onPressEnterKeyAtEmail = (event) => {
    if (event.key === 'Enter') {
      event.preventDefault();
      checkDuplicateEmail();
    }
  };

  //이메일 중복 확인
  const checkDuplicateEmail = async () => {
    if (state.email.length < 8) {
      alert('이메일을 8자 이상 입력해주세요.');
      return;
    }

    try {
      const response = await handleDuplicate(state.email);
      console.log('log', response.data.code);
      console.log('log', response.data.data);

      if (response.data.code === 2002) {
        alert(response.data.message);
      } else {
        alert('사용 가능한 이메일 입니다.');
      }
    } catch (error) {
      console.log(error.message);
      console.error('Email check failed', error);
      alert('이메일 중복 확인에 실패했습니다.');
    }
  };

  //계좌 개수 증가
  const handleAddAccount = () => {
    setState({
      ...state,
      accounts: [...state.accounts, ''],
      banks: [...state.banks, ''],
    });
  };

  //계좌 삭제
  const handleRemoveAccount = (index) => {
    const updatedAccounts = [...state.accounts];
    const updatedBanks = [...state.banks];
    updatedAccounts.splice(index, 1);
    updatedBanks.splice(index, 1);
    setState({ ...state, accounts: updatedAccounts, banks: updatedBanks });
  };

  //계좌 변경
  const handleAccountChange = (index, accountNumber, bankName) => {
    const updatedAccounts = [...state.accounts];
    const updatedBanks = [...state.banks];
    updatedAccounts[index] = accountNumber;
    updatedBanks[index] = bankName;
    setState({ ...state, accounts: updatedAccounts, banks: updatedBanks });
  };

  //비밀번호 재확인 성공여부 판단
  const passwordCheck = state.password === state.repassword;

  // 회원가입 버튼 클릭 시
  const onSignUpButtonClick = async (event) => {
    event.preventDefault();
    if (state.email.trim() === '') {
      alert('이메일을 입력해주세요');
      return;
    }
    if (state.password.trim() === '') {
      alert('비밀번호를 입력해주세요');
      return;
    }
    if (state.name.trim() === '') {
      alert('이름을 입력해주세요');
      return;
    }
    if (state.age.trim() === '') {
      alert('나이를 입력해주세요');
      return;
    }
    if (state.salary.trim() === '') {
      alert('연봉을 입력해주세요');
      return;
    }
    if (state.accounts.length < 1) {
      alert('계좌를 1개 이상 입력해주세요');
      return;
    }
    if (state.accounts.some((account) => account.trim() === '')) {
      alert('모든 계좌를 입력해주세요');
      return;
    }

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
        if (response.data.code === 1000) {
          console.log('SignUp success');
          alert('회원가입에 성공하셨습니다');
          navigate('/login', { replace: true });
        } else {
          console.error(response.data.code + ' ' + response.data.message);
          alert('회원가입에 실패하셨습니다');
        }
      } catch (error) {
        console.error('SignUp failed', error);
        alert('회원가입에 실패하셨습니다');
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
      <h1 className="user-signup-title"></h1>

      <div className="signup-container-box">
        {/* 이메일 입력 */}
        <div className="user-signup-input signup-email-input">
          <input
            className="signup-input-box signup-email-box"
            placeholder="이메일"
            variant="outlined"
            value={state.email}
            onChange={(e) => setState({ ...state, email: e.target.value })}
            onKeyDown={onPressEnterKeyAtEmail}
          />

          <button
            className="signup-button duplicatecheck-button"
            style={{ fontSize: 'smaller', padding: '5px 10px' }}
            onClick={checkDuplicateEmail}
          >
            중복확인
          </button>
        </div>

        {/* 비밀번호 입력 */}
        <div className="user-signup-input signup-pw-input">
          <input
            type="password"
            className="signup-input-box signup-pw-box"
            placeholder="비밀번호"
            variant="outlined"
            value={state.password}
            onChange={(e) => setState({ ...state, password: e.target.value })}
          />
        </div>

        {/* 비밀번호 확인 */}
        <div className="user-signup-input signup-repw-input">
          <input
            type="password"
            className="signup-input-box signup-repw-box"
            placeholder="비밀번호 확인"
            variant="outlined"
            value={state.repassword}
            onChange={(e) => setState({ ...state, repassword: e.target.value })}
          />
        </div>

        {/* 이름 입력 */}
        <div className="user-signup-input signup-name-input">
          <input
            type="text"
            className="signup-input-box signup-name-box"
            placeholder="이름"
            variant="outlined"
            value={state.name}
            onChange={(e) => setState({ ...state, name: e.target.value })}
          />
        </div>

        {/* 나이 입력 */}
        <div className="user-signup-input">
          <div className="user-signup-input signup-age-input">
            <input
              type="number"
              className="signup-input-box signup-age-box"
              placeholder="나이"
              variant="outlined"
              value={state.age}
              onChange={(e) => setState({ ...state, age: e.target.value })}
            />
          </div>
          {/* 소득 입력 */}
          <div className="user-signup-input signup-salary-input">
            <input
              type="number"
              className="signup-input-box signup-salary-box"
              placeholder="연봉"
              variant="outlined"
              value={state.salary}
              onChange={(e) => setState({ ...state, salary: e.target.value })}
            />
          </div>
        </div>

        <div className="user-signup-input-sub">
          {/* 은행 입력 */}
          <div className="signup-input-bank-container">
            {state.banks.map((bank, index) => (
              <div
                key={index}
                className="signup-bank-input"
                style={{ '--banktop': index * -1 + 'px' }}
              >
                <input
                  type="text"
                  className="signup-input-box signup-bank-box"
                  placeholder={`은행`}
                  variant="outlined"
                  value={bank}
                  onClick={() => openModal(index)}
                />
              </div>
            ))}
          </div>

          {/* 계좌 입력 */}
          <div className="signup-input-account-container">
            {state.accounts.map((account, index) => (
              <div
                key={index}
                className="signup-account-input"
                style={{ '--accounttop': index * -1 + 'px' }}
              >
                <input
                  type="number"
                  className="signup-input-box signup-account-box"
                  placeholder={`계좌 ${index + 1}`}
                  variant="outlined"
                  value={account}
                  onClick={() => openModal(index)}
                />

                {/* 계좌 제거 버튼 */}
                <Icon
                  icon="ph:minus-fill"
                  color="#007bff"
                  width="30"
                  height="30"
                  onClick={() => handleRemoveAccount(index)}
                />
              </div>
            ))}
          </div>
        </div>

        {/* 계좌 추가 버튼 */}
        <button
          className="signup-button account-plus-btn"
          onClick={handleAddAccount}
        >
          계좌 추가
        </button>
      </div>

      {/* 가입하기 버튼 */}
      <button
        className="signup-button signup-btn"
        onClick={onSignUpButtonClick}
      >
        가입하기
      </button>

      {/* 모달 창 */}
      {isModalOpen && (
        <AccountModal
          closeModal={closeModal}
          handleAccountChange={handleAccountChange}
          index={selectedAccountIndex}
          accounts={state.accounts}
        />
      )}
    </div>
  );
};

export default SignUpTemplate;
