"use strict";(self.webpackChunkmy_website=self.webpackChunkmy_website||[]).push([[900],{3286:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>d,contentTitle:()=>r,default:()=>p,frontMatter:()=>s,metadata:()=>i,toc:()=>c});const i=JSON.parse('{"id":"ApplicationState","title":"ApplicationState","description":"The ApplicationState class manages the overall state of the application, including rooms, doctors, patients, and appointments. It provides methods for adding, removing, saving, and loading application data.","source":"@site/docs/ApplicationState.md","sourceDirName":".","slug":"/ApplicationState","permalink":"/docs/ApplicationState","draft":false,"unlisted":false,"tags":[],"version":"current","sidebarPosition":3,"frontMatter":{"title":"ApplicationState","sidebar_label":"ApplicationState","sidebar_position":3},"sidebar":"tutorialSidebar","previous":{"title":"Room","permalink":"/docs/objects/Room"},"next":{"title":"FileIO","permalink":"/docs/FileIO"}}');var a=n(4848),o=n(8453);const s={title:"ApplicationState",sidebar_label:"ApplicationState",sidebar_position:3},r="ApplicationState",d={},c=[{value:"Features",id:"features",level:2},{value:"Properties",id:"properties",level:2},{value:"Methods",id:"methods",level:2},{value:"Usage Example",id:"usage-example",level:2},{value:"Conclusion",id:"conclusion",level:2}];function l(e){const t={code:"code",h1:"h1",h2:"h2",header:"header",li:"li",p:"p",pre:"pre",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,o.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"applicationstate",children:"ApplicationState"})}),"\n",(0,a.jsxs)(t.p,{children:["The ",(0,a.jsx)(t.code,{children:"ApplicationState"})," class manages the overall state of the application, including rooms, doctors, patients, and appointments. It provides methods for adding, removing, saving, and loading application data."]}),"\n",(0,a.jsx)(t.h2,{id:"features",children:"Features"}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:"Maintains in-memory lists of rooms, doctors, patients, and appointments."}),"\n",(0,a.jsx)(t.li,{children:"Supports adding and removing entities dynamically."}),"\n",(0,a.jsx)(t.li,{children:"Handles JSON-based persistence for application state."}),"\n",(0,a.jsx)(t.li,{children:"Ensures proper state linkage between doctors, patients, and their appointments."}),"\n",(0,a.jsx)(t.li,{children:"Uses private constructors to enforce singleton-like behavior."}),"\n"]}),"\n",(0,a.jsx)(t.h2,{id:"properties",children:"Properties"}),"\n",(0,a.jsxs)(t.table,{children:[(0,a.jsx)(t.thead,{children:(0,a.jsxs)(t.tr,{children:[(0,a.jsx)(t.th,{children:"Property"}),(0,a.jsx)(t.th,{children:"Type"}),(0,a.jsx)(t.th,{children:"Description"})]})}),(0,a.jsxs)(t.tbody,{children:[(0,a.jsxs)(t.tr,{children:[(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"rooms"})}),(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"List<Room>"})}),(0,a.jsx)(t.td,{children:"List of all available rooms."})]}),(0,a.jsxs)(t.tr,{children:[(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"doctors"})}),(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"List<Doctor>"})}),(0,a.jsx)(t.td,{children:"List of registered doctors."})]}),(0,a.jsxs)(t.tr,{children:[(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"patients"})}),(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"List<Patient>"})}),(0,a.jsx)(t.td,{children:"List of registered patients."})]}),(0,a.jsxs)(t.tr,{children:[(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"appointments"})}),(0,a.jsx)(t.td,{children:(0,a.jsx)(t.code,{children:"List<Appointment>"})}),(0,a.jsx)(t.td,{children:"List of all scheduled appointments."})]})]})]}),"\n",(0,a.jsx)(t.h2,{id:"methods",children:"Methods"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void addRoom(Room room)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Adds a new room to the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void addDoctor(Doctor doctor)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Adds a new doctor to the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void addPatient(Patient patient)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Adds a new patient to the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void addAppointment(Appointment appointment)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Adds an appointment and updates the corresponding doctor and patient records."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void removeRoom(Room room)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Removes a room from the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void removeDoctor(Doctor doctor)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Removes a doctor from the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void removePatient(Patient patient)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Removes a patient from the system."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void removeAppointment(Appointment appointment)\n"})}),"\n",(0,a.jsx)(t.p,{children:"Removes an appointment and updates the corresponding doctor and patient records."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public void saveState() throws JsonFileSaveException\n"})}),"\n",(0,a.jsx)(t.p,{children:"Saves the current application state to JSON files."}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:"public static ApplicationState loadState() throws JsonFileLoadException\n"})}),"\n",(0,a.jsx)(t.p,{children:"Loads the application state from JSON files if they exist; otherwise, returns a new empty state."}),"\n",(0,a.jsx)(t.h2,{id:"usage-example",children:"Usage Example"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-java",children:'ApplicationState state = ApplicationState.loadState();\r\n\r\nDoctor doctor = new Doctor("John", "Doe", "john.doe@example.com");\r\nPatient patient = new Patient("English", "Jane", "Doe", "jane.doe@example.com", "123-456-7890", "123 Main St");\r\nRoom room = new Room("Room 101");\r\nAppointment appointment = Appointment.create(patient, doctor, room, LocalDateTime.now(), Duration.ofMinutes(30), "Checkup");\r\n\r\nstate.addDoctor(doctor);\r\nstate.addPatient(patient);\r\nstate.addRoom(room);\r\nstate.addAppointment(appointment);\r\n\r\nstate.saveState();\n'})}),"\n",(0,a.jsx)(t.h2,{id:"conclusion",children:"Conclusion"}),"\n",(0,a.jsxs)(t.p,{children:["The ",(0,a.jsx)(t.code,{children:"ApplicationState"})," class serves as a centralized storage and management unit for key application data, ensuring data persistence and structured access to doctors, patients, and appointments."]})]})}function p(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(l,{...e})}):l(e)}},8453:(e,t,n)=>{n.d(t,{R:()=>s,x:()=>r});var i=n(6540);const a={},o=i.createContext(a);function s(e){const t=i.useContext(o);return i.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function r(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:s(e.components),i.createElement(o.Provider,{value:t},e.children)}}}]);