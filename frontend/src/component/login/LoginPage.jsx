import React, { PureComponent } from "react";
import style from "../../css/login/login.module.css";
import LoginBox from "./LoginBox";
class LoginPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style.article__inner}>
            <LoginBox />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default LoginPage;
