import React, { PureComponent } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import DefaultPage from "../default/DefaultPage";
import LoginPage from "../login/LoginPage";
import "../../css/elementSetting.css";
import SignUpPage from "../signUp/SignUpPage";
import MakeStudyPage from "../study/makeStudy/MakeStudyPage";
import InputUserDetailsPage from "../inputUserDetails/InputUserDetailsPage";
import FinishMakeStudyPage from "../study/makeStudy/finishMakeStudy/FinishMakeStudyPage";
import DetailedStudyPage from "../study/detailedstudy/DetailedStudyPage";

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
            <Route path="/input" component={InputUserDetailsPage} />
            <Route exact path="/study/make" component={MakeStudyPage} />
            <Route path="/study/make/finish" component={FinishMakeStudyPage} />
            <Route path="/study/detail" component={DetailedStudyPage} />
          </Switch>
          <Footer />
        </Router>
      </React.Fragment>
    );
  }
}

export default Layout;
