import React, { useState } from 'react';
import './BankSignupTemplate.css';
import { useNavigate } from 'react-router-dom';
import handleBankSignup from '../../utils/handleBankSignup';

const BankSignUpTemplate = () => {
  const [state, setState] = useState({
    bankCode: '',
    accountNumber: '',
    accountPassword: '',
    accountRepassword: '',
    balance: '',
  });

  const navigate = useNavigate();

  //비밀번호 재확인 성공여부 판단
  const passwordCheck = state.password === state.repassword;

  //계정 생성 버튼 클릭 시
  const onSignUpButtonClick = async (event) => {
    event.preventDefault();
    if (state.bankCode.trim() === '') {
      alert('은행코드를 입력해주세요');
      return;
    }
    if (state.accountNumber.trim() === '') {
      alert('계좌 번호를 입력해주세요');
      return;
    }
    if (state.accountPassword.trim() === '') {
      alert('계좌 비밀번호를 입력해주세요');
      return;
    }
    if (state.balance.trim() === '') {
      alert('잔액을 입력해주세요');
      return;
    }
    if (passwordCheck) {
      try {
        const response = await handleBankSignup(
          state.bankCode,
          state.accountNumber,
          state.accountPassword,
          state.balance,
        );
        if (response.data.code === 1000) {
          console.log('SignUp success');
          alert('계좌 생성에 성공하셨습니다');
          navigate('/bank/login', { replace: true });
        } else {
          console.error(response.data.code + ' ' + response.data.message);
          alert('계좌 생성에 실패하셨습니다');
        }
      } catch (error) {
        console.error('SignUp failed', error);
        alert('계좌 생성에 실패하셨습니다');
      }
    } else {
      alert('계좌 비밀번호가 일치하지 않습니다');
    }
  };

  return (
    <div className="bank-signup">
      <p className="bank-signup-title">계좌생성</p>

      {/* 은행 입력 */}
      <div className="bank-signup-input1">
        <input
          type="number"
          className="input"
          placeholder="은행"
          variant="outlined"
          value={state.bankCode}
          onChange={(e) => setState({ ...state, bankCode: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 계좌 번호 입력 */}
      <div className="bank-signup-input1">
        <input
          type="number"
          className="input"
          placeholder="계좌 번호"
          variant="outlined"
          value={state.accountNumber}
          onChange={(e) =>
            setState({ ...state, accountNumber: e.target.value })
          }
        />
        <span className="border"></span>
      </div>

      {/* 비밀번호 입력 */}
      <div className="bank-signup-input1">
        <input
          type="password"
          className="input"
          placeholder="비밀번호"
          variant="outlined"
          value={state.accountPassword}
          onChange={(e) =>
            setState({ ...state, accountPassword: e.target.value })
          }
        />
        <span className="border"></span>
      </div>
      {/* 비밀번호 확인 */}
      <div className="bank-signup-input1">
        <input
          type="password"
          className="input"
          placeholder="비밀번호 확인"
          variant="outlined"
          value={state.accountRepassword}
          onChange={(e) =>
            setState({ ...state, accountRepassword: e.target.value })
          }
        />
        <span className="border"></span>
      </div>

      {/* 현재 잔액 입력 */}
      <div className="bank-signup-input1">
        <input
          type="number"
          className="input"
          placeholder="현재 잔액"
          variant="outlined"
          value={state.balance}
          onChange={(e) => setState({ ...state, balance: e.target.value })}
        />
        <span className="border"></span>
      </div>
      <div className="bank-signup-input2">
        <button className="bank-signup-button" onClick={onSignUpButtonClick}>
          생성하기
        </button>
      </div>
    </div>
  );
};

export default BankSignUpTemplate;
