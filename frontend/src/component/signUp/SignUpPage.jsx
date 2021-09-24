import React, { PureComponent } from "react";
import SignUpBox from "./SignUpBox";
import "../../css/login/login.css";
class SignUpPage extends PureComponent {
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

export default SignUpPage;
