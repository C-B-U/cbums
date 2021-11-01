import React, { PureComponent } from 'react';
import style from "../../../../css/study/study_make_complete/study_make_complete.module.css";
import OpenedStudyContents from './OpenedStudyContents';
import OpenedStudyHeadCount from './OpenedStudyHeadCount';
import OpenedStudyTag from './OpenedStudyTag';
import OpenedStudyTitle from './OpenedStudyTitle';
import OpenedStudyWriter from './OpenedStudyWriter';

class OpenedStudyPage extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <main>
              <div className={style.main_wrap}>
                <div className={style.main__studyComplete}>
                  <div className={style["main__studyComplete-wrap"]}>
                        <OpenedStudyTitle />
                        <OpenedStudyWriter />
                        <OpenedStudyTag />
                        <OpenedStudyContents />
                        <OpenedStudyHeadCount />
                  </div>
                </div>
              </div>
            </main>
          </React.Fragment>
        );
    }
}

export default OpenedStudyPage;


