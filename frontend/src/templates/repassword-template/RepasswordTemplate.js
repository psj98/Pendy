import React, { useState } from 'react';
import './RepasswordTemplate.css';

const RepasswordTemplate = () => {
  const [state, setState] = useState({ password: '', repassword: '' });

  return (
    <div className="repassword">
      <h1 className="repassword-title">비밀번호 변경</h1>

      <div className="repassword-container-box">
        {/* 비밀번호 입력 */}
        <div className="repassword-input repassword-first-input">
          <input
            className="repassword-input-box repassword-first-box"
            type="password"
            placeholder="비밀번호"
            variant="outlined"
            value={state.password}
            onChange={(e) => setState({ ...state, password: e.target.value })}
          />
          <span className="border"></span>
        </div>
        {/* 비밀번호 재입력 */}
        <div className="repassword-input repassword-second-input">
          <input
            className="repassword-input-box repassword-second-box"
            type="password"
            placeholder="비밀번호 재입력"
            variant="outlined"
            value={state.repassword}
            onChange={(e) => setState({ ...state, repassword: e.target.value })}
          />
        </div>
        {/* 비밀번호 변경 버튼 */}
        <div className="repassword-input repassword-button-div">
          <button className="repassword-button">비밀번호 변경</button>
        </div>
      </div>
    </div>
  );
};

export default RepasswordTemplate;
