import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import "../../css/default/default.css";
import Banner from "./Banner";
import Notice from "./Notice";
import Study from "./Study";
import Member from "./Member";
class DefaultPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <main>
            <div class="main-wrap">
              <Banner />
              <Study />
              <Notice />
            </div>
          </main>
        </Default>
        <Mobile>
          <main>
            <div class="main-wrap">
              <Member />
              <Banner />
              <Study />
              <Notice />
            </div>
          </main>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default DefaultPage;
