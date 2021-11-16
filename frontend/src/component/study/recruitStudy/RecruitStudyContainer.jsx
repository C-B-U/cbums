import React, { PureComponent } from "react";
import RecruitStudyPost from "./RecruitStudyPost";

class RecruitStudyContainer extends PureComponent {
    render() {
        return (
          <React.Fragment>
           <RecruitStudyPost
             studyTitle="프로젝트 하실분 구해요"
              userName="이부엉"
              postDate="2021.11.16" />
            <RecruitStudyPost
              studyTitle="자바 스터디 모집합니다"
              userName="김부엉"
              postDate="2021.11.15"
            />
          </React.Fragment>
        );
    }
}

export default RecruitStudyContainer;
