import React, { PureComponent } from "react";
import Kakao from "../../images/login/kakaoLogo.svg";
import Google from "../../images/login/googleLogo.svg";
import Naver from "../../images/login/naverLogo.svg";
import style from "../../css/login/login.module.css";
import LoginButton from "./LoginButton";

class LoginButtonList extends PureComponent {
 
  render() {
    const naverBackgroundColor = { backgroundColor: "#03C75A", cursor:"pointer" };
    const naverTitleColor = { color: "#ffffff" };
    const kakaoBackgroundColor = { backgroundColor: "#FEE500", cursor:"pointer"  };
    const kakaoTitleColor = { color: "#3c1e1e" };
    const googleBackgroundColor = { backgroundColor: "#ffffff" , cursor:"pointer" };
    const googleTitleColor = { color: "#757575" };
    
    return (
      <React.Fragment>
        <div className={style.button_container}>
          <LoginButton
            className={style.loginButton}
            bgStyle={naverBackgroundColor}
            tStyle={naverTitleColor}
            width="20"
            alt="네이버로고"
            title="네이버 로그인"
            src={Naver}
          />
            <LoginButton 
              className={style.loginButton}
              bgStyle={kakaoBackgroundColor}
              tStyle={kakaoTitleColor}
              width="20"
              alt="카카오로고"
              title="카카오 로그인"
              src={Kakao}
            />
          <LoginButton
            className={style.loginButton}
            bgStyle={googleBackgroundColor}
            tStyle={googleTitleColor}
            title="구글 로그인"
            alt="구글로고"
            src={Google}
            width="23"
          />
        </div>
      </React.Fragment>
    );
  }
}

export default LoginButtonList;
