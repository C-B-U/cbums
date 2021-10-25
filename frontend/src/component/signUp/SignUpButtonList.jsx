import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import Kakao from "../../images/login/kakaoLogo.svg";
import Google from "../../images/login/googleLogo.svg";
import Naver from "../../images/login/naverLogo.svg";
import style from "../../css/login/login.module.css";
import AuthButton from "../login/AuthButton";

class SignUpButtonList extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.button_container}>
          <Link to="/">
            <AuthButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#03C75A" }}
              tStyle={{ color: "#ffffff" }}
              title="네이버로 시작하기"
              width="20"
              alt="네이버로고"
              src={Naver}
            />
          </Link>
          <Link to="/">
            <AuthButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#FEE500" }}
              tStyle={{ color: "#3c1e1e" }}
              title="카카오로 시작하기"
              width="20"
              alt="카카오로고"
              src={Kakao}
            />
          </Link>
          <Link to="/">
            <AuthButton
              className={style.loginButton}
              bgStyle={{ backgroundColor: "#ffffff" }}
              tStyle={{ color: "#757575" }}
              title="구글로 시작하기"
              width="23"
              alt="구글로고"
              src={Google}
            />
          </Link>
        </div>
      </React.Fragment>
    );
  }
}

export default SignUpButtonList;
