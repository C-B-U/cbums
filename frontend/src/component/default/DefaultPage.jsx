import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import style from "../../css/default/default.module.css";
import Banner from "./Banner";
import Notice from "./Notice";
import Study from "./Study";
import Member from "./Member";
import Line from "./Line";
class DefaultPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <main>
            <div className={style['main-wrap']}>
              <Banner />
              <Study />
              <Notice />
            </div>
          </main>
        </Default>
        <Mobile>
          <main>
            <div className={style['main-wrap']}>
              <Member />
              <Banner />
              <Study />
              <Line />
              <Notice />
            </div>
          </main>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default DefaultPage;
