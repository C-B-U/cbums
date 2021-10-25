import React, { PureComponent } from "react";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";
import SignUpBox from "./SignUpInputBox";

class SignUpInputPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style['article__inner']}>
            <SignUpBox />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default SignUpInputPage;
