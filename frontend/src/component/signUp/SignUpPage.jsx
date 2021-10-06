import React, { PureComponent } from "react";
import SignUpBox from "./SignUpBox";
import style from "../../css/login/login.module.css";
class SignUpPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style.article__inner}>
            <SignUpBox />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default SignUpPage;
