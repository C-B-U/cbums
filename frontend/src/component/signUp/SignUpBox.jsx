import React, { PureComponent } from "react";
import BoxList from "../login/BoxList";
import BoxTitle from "../login/BoxTitle";
import style from "../../css/login/login.module.css";

class SignUpBox extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <BoxTitle
          title="회원가입"
          titleClass={style.article__boxTitle}
          imageClass={style.article__boxDeco}
        />
        <BoxList />
      </React.Fragment>
    );
  }
}

export default SignUpBox;
