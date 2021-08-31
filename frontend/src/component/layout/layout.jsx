import React, { PureComponent } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import DefaultPage from "../default/DefaultPage";
import "../../css/elementSetting.css";

class Layout extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Router>
          <Header />
          <Switch>
            <Route path="/" component={DefaultPage} />
          </Switch>
          <Footer />
        </Router>
        {/* 폰트 변경 필요 */}
      </React.Fragment>
    );
  }
}

export default Layout;
