import React, { PureComponent } from "react";
import style from "../../css/login/login.module.css";
import SingUpTitle from "./SingUpTitle";
import SignUpContent from "./SignUpContent";

class SignUpPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style.article__inner}>
            <SingUpTitle
              title="회원가입"
              titleClass={style.article__boxTitle}
              imageClass={style.article__boxDeco}
            />
            <SignUpContent />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default SignUpPage;
