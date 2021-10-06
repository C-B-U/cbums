import React, { PureComponent } from "react";
import Box from "./Box";
import style from "../../css/login/login.module.css";
class LoginPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style.article__inner}>
            <Box />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default LoginPage;
