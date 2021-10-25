import React, { PureComponent } from "react";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";
import AuthBoxTitle from "../login/AuthBoxTitle";
import InputContent from "./InputContent";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <AuthBoxTitle
          title="추가 정보 입력"
          titleClass={style.article__boxTitle}
          imageClass={style.article__boxDeco}
        />
        <InputContent />
      </React.Fragment>
    );
  }
}

export default SignUpBox;
