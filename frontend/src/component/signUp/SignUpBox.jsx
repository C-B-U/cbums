import React, { PureComponent } from "react";
import style from "../../css/login/login.module.css";
import AuthBoxList from "../login/AuthBoxList";
import AuthBoxTitle from "../login/AuthBoxTitle";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <AuthBoxTitle
          title="회원가입"
          titleClass={style.article__boxTitle}
          imageClass={style.article__boxDeco}
        />
        <AuthBoxList />
      </React.Fragment>
    );
  }
}

export default SignUpBox;
