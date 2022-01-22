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
import RecruitStudyPage from "../study/recruitStudy/RecruitStudyPage";
import StudyNavPage from "../study/studyNav/StudyNavPage";
import RecruitDetailStudyPage from "../study/recruitDetailStudy/RecruitDetailStudyPage";
import StudyActivityPage from "../study/studyActivity/StudyActivityPage";
import WriteStudyActivityPage from "../study/WriteStudyActivity/WriteStudyActivityPage";
import ReadStudyActivityPage from "../study/ReadStudyActivity/ReadStudyActivityPage";


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
            <Route exact path="/study/recruit" component={RecruitStudyPage} />
            <Route exact path="/study/make" component={MakeStudyPage} />
            <Route
              exact
              path="/study/recruit/:projectId"
              component={RecruitDetailStudyPage}
            />
            <Route exact path="/study/:projectId" component={StudyNavPage} />
            <Route
              exact
              path="/study/:projectId/activity"
              component={StudyActivityPage}
            />
            <Route
              exact
              path="/study/:projectId/activity/write"
              component={WriteStudyActivityPage}
            />
            <Route
              exact
              path="/study/:projectId/activity/read"
              component={ReadStudyActivityPage}
            />
          </Switch>
          <Footer />
        </Router>
      </React.Fragment>
    );
  }
}

export default Layout;
