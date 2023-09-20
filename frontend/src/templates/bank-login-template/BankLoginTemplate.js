import React, { useState } from 'react';
import './BankLoginTemplate.css';
import { Link, useNavigate } from 'react-router-dom';
import handleBankLogin from '../../utils/handleBankLogin';

const BankLoginTemplate = () => {
  const [state, setState] = useState({
    accountNumber: '',
    bankCode: '',
    accountPassword: '',
  });
  const navigate = useNavigate();

  //로그인 버튼 동작
  const onLoginButtonClick = async (event) => {
    event.preventDefault();
    try {
      const response = await handleBankLogin(
        state.accountNumber,
        state.bankCode,
        state.accountPassword,
      );
      if (response.data.code === 1000) {
        console.log(response.data);
        sessionStorage.setItem(
          'accountNumber',
          response.data.data.accountNumber,
        );
        sessionStorage.setItem('bankCode', response.data.data.bankCode);
        sessionStorage.setItem('balance', response.data.data.balance);
        console.log('Login success');
        alert('로그인에 성공하셨습니다.');
        navigate('/bank', { replace: true });
      } else {
        console.error(response.data.code + ' ' + response.data.message);
        alert('로그인에 실패하셨습니다');
        setState({ accountNumber: '', bankCode: '', accountPassword: '' });
      }
    } catch (error) {
      console.error('Login failed');
      alert('로그인에 실패하셨습니다');
      setState({ accountNumber: '', bankCode: '', accountPassword: '' });
    }
  };

  return (
    <div className="bank-login">
      <h2 className="bank-login-title">PENDY BANK</h2>

      {/* 은행 입력 */}
      <div className="bank-login-input">
        <input
          className="input"
          placeholder="은행 코드"
          variant="outlined"
          value={state.bankCode}
          onChange={(e) => setState({ ...state, bankCode: e.target.value })}
        />
        <span className="border"></span>
      </div>

      {/* 계좌번호 입력 */}
      <div className="bank-login-input">
        <input
          className="input"
          placeholder="계좌번호"
          variant="outlined"
          value={state.accountNumber}
          onChange={(e) =>
            setState({ ...state, accountNumber: e.target.value })
          }
        />
        <span className="border" />
      </div>

      {/* 계좌 비밀번호 입력 */}
      <div className="bank-login-input">
        <input
          type="password"
          className="input"
          placeholder="계좌 비밀번호"
          variant="outlined"
          value={state.accountPassword}
          onChange={(e) =>
            setState({ ...state, accountPassword: e.target.value })
          }
        />
        <span className="border" />
      </div>
      <br />

      {/* 로그인 버튼 */}
      <div className="bank-login-input">
        <button className="login-button" onClick={onLoginButtonClick}>
          로그인
        </button>
      </div>

      {/* 계좌 생성 링크 */}
      <div className="bank-login-input">
        <Link to="/bank/signup" className="login-link">
          계좌생성
        </Link>
      </div>
    </div>
  );
};

export default BankLoginTemplate;
