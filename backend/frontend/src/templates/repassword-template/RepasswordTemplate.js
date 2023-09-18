import React, { useState } from 'react';
import './RepasswordTemplate.css';

const RepasswordTemplate = () => {
  const [state, setState] = useState({ password: '', repassword: '' });

  return (
    <div className="repassword">
      <p className="repassword-title">비밀번호 재설정</p>
      <div className="repassword-input">
        <input
          className="input"
          type="password"
          placeholder="비밀번호를 입력해주세요"
          variant="outlined"
          value={state.password}
          onChange={(e) => setState({ ...state, password: e.target.value })}
        />
        <span className="border"></span>
      </div>
      <div className="repassword-input">
        <input
          className="input"
          type="password"
          placeholder="비밀번호를 다시 한번 입력해주세요"
          variant="outlined"
          value={state.repassword}
          onChange={(e) => setState({ ...state, repassword: e.target.value })}
        />
        <span className="border"></span>
      </div>
      <br />
      <div className="repassword-input">
        <button className="repassword-button">재설정하기</button>
      </div>
    </div>
  );
};

export default RepasswordTemplate;
