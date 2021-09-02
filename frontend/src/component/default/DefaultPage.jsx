import React, { PureComponent } from "react";
import "../../css/default/default.css";
import Banner from "./Banner";
import Study from "./Study";
class DefaultPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div class="main-wrap">
            <Banner />
            <Study />
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default DefaultPage;
