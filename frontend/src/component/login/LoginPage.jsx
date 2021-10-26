import React, { PureComponent } from "react";
import style from "../../css/login/login.module.css";
import LoginTitle from "./LoginTitle";
import LoginContent from "./LoginContent";

class LoginPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style.article__inner}>
            <LoginTitle
              title="로그인"
              titleClass={style.article__boxTitle}
              imageClass={style.article__boxDeco}
            />
            <LoginContent />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default LoginPage;
