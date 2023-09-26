import React from 'react';
import './Header.css';
import { Link, useNavigate, useLocation } from 'react-router-dom';

const Header = ({ isLoggedIn }) => {
  const navigate = useNavigate();
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path;
  };

  const onClickLogout = () => {
    console.log('logout');
    localStorage.removeItem('accessToken');
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('name');
    sessionStorage.removeItem('age');
    sessionStorage.removeItem('salary');
    sessionStorage.removeItem('accountList');
    navigate('/', { replace: true });
  };

  return (
    <div className="header">
      <div className="header-logo-container">
        <Link to={'/'} className="header-link-content">
          PENDY
        </Link>
      </div>
      <div className="header-menu-container">
        <div className="menu-list">
          {isLoggedIn ? (
            <div className="sub-menu">
              <div className="sub-menu-container">
                <Link
                  to={'/setting'}
                  className={`link-content ${
                    isActive('/setting') ? 'active-link' : ''
                  }`}
                >
                  개인정보
                </Link>
              </div>
              <div className="sub-menu-container">
                <Link
                  to={'/goal'}
                  className={`link-content ${
                    isActive('/goal') ? 'active-link' : ''
                  }`}
                >
                  목표설정
                </Link>
              </div>
              <div className="sub-menu-container" onClick={onClickLogout}>
                로그아웃
              </div>
            </div>
          ) : (
            <div className="sub-menu">
              <div className="sub-menu-container">
                <Link
                  to={'/login'}
                  className={`link-content ${
                    isActive('/login') ? 'active-link' : ''
                  }`}
                >
                  로그인
                </Link>
              </div>
              <div className="sub-menu-container">
                <Link
                  to={'/login/signup'}
                  className={`link-content ${
                    isActive('/login/signup') ? 'active-link' : ''
                  }`}
                >
                  회원가입
                </Link>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Header;
