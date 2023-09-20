import React, { useState } from 'react';
import './SignUpTemplate.css';
import { Icon } from '@iconify/react';
import handleSignup from '../../utils/handleSignup';
import AccountModal from '../../components/signup/account-modal/AccountModal';
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

  //이메일 중복 확인
  const checkDuplicateEmail = async () => {
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
      <p className="signup-title">회원가입</p>

      {/* 이메일 입력 */}
      <div className="signup-input1">
        <input
          type="text"
          className="input"
          placeholder="이메일"
          variant="outlined"
          value={state.email}
          onChange={(e) => setState({ ...state, email: e.target.value })}
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
      <div className="signup-input1">
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
      <div className="signup-input1">
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
      <div className="signup-input1">
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
        {/* 은행 입력 */}
        <div className="signup-input-sub1">
          {state.banks.map((bank, index) => (
            <div key={index} className="signup-input2">
              <input
                type="text"
                className="input"
                placeholder={`은행`}
                variant="outlined"
                value={bank}
                onClick={() => openModal(index)}
              />
              <span className="border"></span>
            </div>
          ))}
        </div>
        &nbsp;&nbsp;
        {/* 계좌 입력 */}
        <div className="signup-input-sub2">
          {state.accounts.map((account, index) => (
            <div key={index} className="signup-input2">
              <input
                type="number"
                className="input"
                placeholder={`계좌 ${index + 1}`}
                variant="outlined"
                value={account}
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
          ))}
        </div>
      </div>

      {/* 계좌 추가 버튼 */}
      <div className="signup-input2">
        <Icon
          icon="ph:plus-fill"
          color="#007bff"
          width="30"
          height="30"
          onClick={handleAddAccount}
        />
      </div>
      <br />
      <div className="signup-input2">
        <button className="signup-button" onClick={onSignUpButtonClick}>
          가입하기
        </button>
      </div>
      {/* 모달 창 */}
      {isModalOpen && (
        <AccountModal
          closeModal={closeModal}
          handleAccountChange={handleAccountChange}
          index={selectedAccountIndex}
        />
      )}
    </div>
  );
};

export default SignUpTemplate;
