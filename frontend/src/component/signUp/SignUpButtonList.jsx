import React, { PureComponent } from "react";
import Kakao from "../../images/login/kakaoLogo.svg";
import Google from "../../images/login/googleLogo.svg";
import Naver from "../../images/login/naverLogo.svg";
import style from "../../css/login/login.module.css";
import SignUpButton from "./SignUpButton";


class SignUpButtonList extends PureComponent {
  render() {
    const naverBackgroundColor = { backgroundColor: "#03C75A" };
    const naverTitleColor = { color: "#ffffff" };
    const kakaoBackgroundColor = { backgroundColor: "#FEE500" };
    const kakaoTitleColor = { color: "#3c1e1e" };
    const googleBackgroundColor = { backgroundColor: "#ffffff" };
    const googleTitleColor = { color: "#757575" };
    return (
      <React.Fragment>
        <div className={style.button_container}>
            <SignUpButton
              className={style.loginButton}
              bgStyle={naverBackgroundColor}
              tStyle={naverTitleColor}
              title="네이버로 시작하기"
              width="20"
              alt="네이버로고"
              src={Naver}
              href="http://localhost:8000/oauth2/authorization/naver"
            />
            <SignUpButton
              className={style.loginButton}
              bgStyle={kakaoBackgroundColor}
              tStyle={kakaoTitleColor}
              title="카카오로 시작하기"
              width="20"
              alt="카카오로고"
              src={Kakao}
              href="http://localhost:8000/oauth2/authorization/kakao"
            />
            <SignUpButton
              className={style.loginButton}
              bgStyle={googleBackgroundColor}
              tStyle={googleTitleColor}
              title="구글로 시작하기"
              width="23"
              alt="구글로고"
              src={Google}
              href="http://localhost:8000/oauth2/authorization/google"
            />
        </div>
      </React.Fragment>
    );
  }
}

export default SignUpButtonList;
