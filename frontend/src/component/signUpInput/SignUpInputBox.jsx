import React, { PureComponent } from "react";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";
import BoxTitle from "../login/BoxTitle";
import InputContent from "./InputContent";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle
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
