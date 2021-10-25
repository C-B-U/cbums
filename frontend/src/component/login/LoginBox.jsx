import React, { PureComponent } from "react";
import style from "../../css/login/login.module.css";
import AuthBoxTitle from "./AuthBoxTitle";
import AuthBoxList from "./AuthBoxList";

class LoginBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <AuthBoxTitle
          title="로그인"
          titleClass={style.article__boxTitle}
          imageClass={style.article__boxDeco}
        />
        <AuthBoxList />
      </React.Fragment>
    );
  }
}

export default LoginBox;
