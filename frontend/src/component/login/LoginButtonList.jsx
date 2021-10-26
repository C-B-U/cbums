import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import Kakao from "../../images/login/kakaoLogo.svg";
import Google from "../../images/login/googleLogo.svg";
import Naver from "../../images/login/naverLogo.svg";
import style from "../../css/login/login.module.css";

import LoginButton from "./LoginButton";

class LoginButtonList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.button_container}>
          <Link to="/">
            <LoginButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#03C75A" }}
              tStyle={{ color: "#ffffff" }}
              width="20"
              alt="네이버로고"
              title="네이버 로그인"
              src={Naver}
            />
          </Link>
          <Link to="/">
            <LoginButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#FEE500" }}
              tStyle={{ color: "#3c1e1e" }}
              width="20"
              alt="카카오로고"
              title="카카오 로그인"
              src={Kakao}
            />
          </Link>
          <Link to="/">
            <LoginButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#ffffff" }}
              tStyle={{ color: "#757575" }}
              title="구글 로그인"
              alt="구글로고"
              src={Google}
              width="23"
            />
          </Link>
        </div>
      </React.Fragment>
    );
  }
}

export default LoginButtonList;
