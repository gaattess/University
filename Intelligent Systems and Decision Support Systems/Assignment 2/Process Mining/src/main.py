from pprint import pprint

import pm4py
from pandas import DataFrame
from pm4py.algo.conformance.tokenreplay import algorithm as token_replay
from pm4py.algo.evaluation.algorithm import apply as evaluate

if __name__ == "__main__":
    # 1. Διαβάζει το event log
    log: DataFrame = pm4py.read_xes("edited_hh104_labour.xes")

    # 2. Εμφανίζουμε τη δομή του trace και του event
    event_log = pm4py.read_xes("edited_hh104_labour.xes", return_legacy_log_object=True)
    for trace in event_log:
        for event in trace:
            print(trace.attributes['concept:name'], '\t', event['concept:name'], '\t', event['time:timestamp'])

    # 3. Μετράμε τα traces
    trace_count = log["case:concept:name"].nunique()
    print(f"Trace count: {trace_count}")

    # 4. Μετράμε τα events
    event_count = len(log)
    print(f"Event count: {event_count}")

    # 5. Τυπώνουμε τα διαφορετικά events από τα οποία αποτελείται το event log
    unique_events = log["concept:name"].unique()
    print(f"Unique events: {unique_events}")

    # 6. Τυπώνουμε τις δραστηριότητες με τις οποίες αρχίζουν και τελειώνουν τα traces και τη συχνότητα εμφάνισής τους
    start_activities = pm4py.get_start_activities(log)
    print(f"Start Activities: {start_activities}")
    end_activities = pm4py.get_end_activities(log)
    print(f"End Activities: {end_activities}")

    # 7. Τυπώνουμε τα case id, activity name, transition, timestamp σε πίνακα
    table = log.filter(items=["case:concept:name", "concept:name", "lifecycle:transition", "time:timestamp"])
    print(table)

    # 8. Φιλτράρουμε και κρατάμε μόνο τα traces που τελειώνουν με 'End' δραστηριότητα
    filtered_log = pm4py.filter_end_activities(log, ["End"])
    print(filtered_log)

    # 9. Ανακαλύπτουμε τα μοντέλα διεργασιών με τους αλγόριθμους alpha miner, heuristics miner, και inductive miner
    #    για το αρχικό event log και για το φιλτραρισμένο
    alpha_discovery = pm4py.discover_petri_net_alpha(log)
    pm4py.save_vis_petri_net(*alpha_discovery, "visualizations/alpha.png")
    filtered_alpha_discovery = pm4py.discover_petri_net_alpha(filtered_log)
    pm4py.save_vis_petri_net(*filtered_alpha_discovery, "visualizations/filtered_alpha.png")

    heuristics_discovery = pm4py.discover_petri_net_heuristics(log)
    pm4py.save_vis_petri_net(*heuristics_discovery, "visualizations/heuristics.png")
    filtered_heuristics_discovery = pm4py.discover_petri_net_heuristics(filtered_log)
    pm4py.save_vis_petri_net(*filtered_heuristics_discovery, "visualizations/filtered_heuristics.png")

    inductive_discovery = pm4py.discover_petri_net_inductive(log)
    pm4py.save_vis_petri_net(*inductive_discovery, "visualizations/inductive.png")
    filtered_inductive_discovery = pm4py.discover_petri_net_inductive(filtered_log)
    pm4py.save_vis_petri_net(*filtered_inductive_discovery, "visualizations/filtered_inductive.png")

    # 10. Evaluate τα event log με βάση τα μοντέλα
    
    # Αρχικά
    
    alpha_eval = evaluate(log, *alpha_discovery)
    print("=== Alpha Algo Evaluation ===")
    pprint(alpha_eval)

    heuristics_eval = evaluate(log, *heuristics_discovery)
    print("=== Heuristics Algo Evaluation ===")
    pprint(heuristics_eval)

    inductive_eval = evaluate(log, *inductive_discovery)
    print("=== Inductive Algo Evaluation ===")
    pprint(inductive_eval)
    
    # Φιλτραρισμένα

    filtered_alpha_eval = evaluate(log, *filtered_alpha_discovery)
    print("=== Alpha Algo Evaluation (filtered data) ===")
    pprint(filtered_alpha_eval)

    filtered_heuristics_eval = evaluate(log, *filtered_heuristics_discovery)
    print("=== Heuristics Algo Evaluation (filtered data) ===")
    pprint(filtered_heuristics_eval)

    filtered_inductive_eval = evaluate(log, *filtered_inductive_discovery)
    print("=== Inductive Algo Evaluation (filtered data) ===")
    pprint(filtered_inductive_eval)

    # 11. Κάνουμε conformance checking με τη Replay fitness μέθοδο
    alpha_replayed = token_replay.apply(log, *alpha_discovery)
    filtered_alpha_replayed = token_replay.apply(log, *filtered_alpha_discovery)

    heuristics_replayed = token_replay.apply(log, *heuristics_discovery)
    filtered_heuristics_replayed = token_replay.apply(log, *filtered_heuristics_discovery)

    inductive_replayed = token_replay.apply(log, *inductive_discovery)
    filtered_inductive_replayed = token_replay.apply(log, *filtered_inductive_discovery)

    print("#Alpha that are Fit: ", [trace['trace_is_fit'] for trace in alpha_replayed].count(True))
    print("#Alpha Filtered that are Fit: ", [trace['trace_is_fit'] for trace in filtered_alpha_replayed].count(True))

    print("#Heuristics that are Fit: ", [trace['trace_is_fit'] for trace in heuristics_replayed].count(True))
    print("#Heuristics Filtered that are Fit: ", [trace['trace_is_fit'] for trace in filtered_heuristics_replayed].count(True))

    print("#Inductive that are Fit: ", [trace['trace_is_fit'] for trace in inductive_replayed].count(True))
    print("#Inductive Filtered that are Fit: ", [trace['trace_is_fit'] for trace in filtered_inductive_replayed].count(True))
