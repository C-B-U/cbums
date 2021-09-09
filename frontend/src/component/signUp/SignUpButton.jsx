import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import Kakao from "../../images/login/kakaoLogo.svg";
import Google from "../../images/login/googleLogo.svg";
import Naver from "../../images/login/naverLogo.svg";
class SignUpButton extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="button_container">
          <Link to="/">
            <div className="loginButton" style={{ backgroundColor: "#03C75A" }}>
              <img width="20" src={Naver} alt="네이버로고" />
              <span style={{ color: "#ffffff" }}>네이버로 시작하기</span>
            </div>
          </Link>
          <Link to="/">
            <div className="loginButton" style={{ backgroundColor: "#FEE500" }}>
              <img width="20" src={Kakao} alt="카카오로고" />
              <span style={{ color: "#3c1e1e" }}>카카오로 시작하기</span>
            </div>
          </Link>
          <Link to="/">
            <div className="loginButton" style={{ backgroundColor: "#ffffff" }}>
              <img width="23" src={Google} alt="구글로고" />
              <span style={{ color: "#757575" }}>구글로 시작하기</span>
            </div>
          </Link>
        </div>
      </React.Fragment>
    );
  }
}

export default SignUpButton;
