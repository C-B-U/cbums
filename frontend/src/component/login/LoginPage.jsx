import React, { PureComponent } from "react";
import Box from "./Box";
import "../../css/login/login.css";
class LoginPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className="article">
          <div className="article__inner">
            <Box />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default LoginPage;
