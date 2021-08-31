import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import "../../css/main.css";
class Main extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <main>
            <div class="main-wrap"></div>
          </main>
        </Default>
        <Mobile>
          <main>
            <div class="main-wrap"></div>
          </main>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Main;
