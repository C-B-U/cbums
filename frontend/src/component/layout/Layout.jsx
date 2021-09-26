import React, { PureComponent } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import DefaultPage from "../default/DefaultPage";
import LoginPage from "../login/LoginPage";
import "../../css/elementSetting.css";
import SignUpPage from "../signUp/SignUpPage";
import SignUpInputPage from "../signUpInput/SignUpInputPage.jsx";
import StudyMakePage from "../study/studyMake/StudyMakePage";

class Layout extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Router>
          <Header />
          <Switch>
            <Route exact path="/" component={DefaultPage} />
            <Route path="/login" component={LoginPage} />
            <Route path="/signup" component={SignUpPage} />
            <Route path="/input" component={SignUpInputPage} />
            <Route path="/studymake" component={StudyMakePage} />
          </Switch>
          <Footer />
        </Router>
      </React.Fragment>
    );
  }
}

export default Layout;
