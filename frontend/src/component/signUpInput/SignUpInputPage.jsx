import React, { PureComponent } from "react";
import "../../css/signUp/inputSignUpContent/inputSignUpContent.css";
import SignUpBox from "./SignUpInputBox";

class SignUpInputPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className="article">
          <div className="article__inner">
            <SignUpBox />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default SignUpInputPage;
